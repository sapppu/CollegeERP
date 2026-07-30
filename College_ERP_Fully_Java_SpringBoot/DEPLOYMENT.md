# Deploy College ERP — Render + Neon (free tier)

**GitHub repo:** [github.com/sapppu/CollegeERP](https://github.com/sapppu/CollegeERP)  
**App folder in repo:** `College_ERP_Fully_Java_SpringBoot/`  
**Local path:** `IdeaProjects/College_ERP_Fully_Java_SpringBoot`

On Render, **Root Directory** must be exactly:

```text
College_ERP_Fully_Java_SpringBoot
```

(Do not leave it empty — the repo root contains many other Java projects.)

---

Stack: **Neon** (PostgreSQL) + **Render** (Docker / Spring Boot).

---

## Before you start

1. Push deploy files to GitHub (`master`). From your machine:

   ```bash
   cd ~/IdeaProjects
   git add College_ERP_Fully_Java_SpringBoot/Dockerfile \
           College_ERP_Fully_Java_SpringBoot/.dockerignore \
           College_ERP_Fully_Java_SpringBoot/render.yaml \
           College_ERP_Fully_Java_SpringBoot/DEPLOYMENT.md \
           College_ERP_Fully_Java_SpringBoot/src/main/resources/application-prod.properties
   git commit -m "Add Render + Neon deployment config"
   git push origin master
   ```

2. Confirm on GitHub:  
   `College_ERP_Fully_Java_SpringBoot/Dockerfile` exists in [CollegeERP](https://github.com/sapppu/CollegeERP).

---

## Part A — Neon (free database)

1. Go to [https://neon.tech](https://neon.tech) and sign up (GitHub login is fine).
2. **New Project** → pick a region close to you → create.
3. Open the project → **Dashboard** → **Connection details**.
4. Copy:
   - **Host** (e.g. `ep-xxxx.region.aws.neon.tech`)
   - **Database name** (often `neondb`)
   - **User**
   - **Password**
5. Build the **JDBC URL** (Neon requires SSL):

   ```text
   jdbc:postgresql://YOUR_HOST/YOUR_DATABASE?sslmode=require
   ```

   Example:

   ```text
   jdbc:postgresql://ep-cool-darkness-123456.us-east-2.aws.neon.tech/neondb?sslmode=require
   ```

   Do **not** put username/password inside this URL; Render env vars below supply them.

6. Optional: in Neon **SQL Editor**, you can run nothing yet — Hibernate will create tables on first app start (`ddl-auto=update`).

**Neon free tier:** DB may **pause** when idle; first connection after pause can take a few seconds.

---

## Part B — Render (free web service)

1. Go to [https://dashboard.render.com](https://dashboard.render.com) and sign up (link **GitHub**).
2. **New +** → **Web Service**.
3. Connect repository: **`sapppu/CollegeERP`** (branch `master`).
4. Settings:

   | Field | Value |
   |--------|--------|
   | **Name** | `college-erp` (any name) |
   | **Region** | Same as Neon if possible |
   | **Root Directory** | **`College_ERP_Fully_Java_SpringBoot`** |
   | **Runtime** | **Docker** |
   | **Dockerfile Path** | `Dockerfile` |
   | **Instance Type** | **Free** |

5. **Environment** → add variables:

   | Key | Value |
   |-----|--------|
   | `SPRING_PROFILES_ACTIVE` | `prod` |
   | `DATABASE_JDBC_URL` | Your Neon JDBC URL from Part A |
   | `DATABASE_USERNAME` | Neon user |
   | `DATABASE_PASSWORD` | Neon password |
   | `APP_UPLOAD_DIR` | `/tmp/uploads/profiles` |

   Render injects **`PORT`** automatically — do not set it.

6. **Advanced** (optional):
   - **Health Check Path**: `/login`

7. Click **Create Web Service**.  
   First deploy builds with Maven inside Docker (**~5–15 minutes**). Watch **Logs**.

8. When status is **Live**, open:

   ```text
   https://YOUR-SERVICE-NAME.onrender.com/
   ```

   or `/login`

**Render free tier:** Service **sleeps** after ~15 minutes without traffic. First request after sleep may take **30–60 seconds**.

---

## Part C — First login / data

- Tables are created automatically on first successful DB connection.
- You need **users in the `users` table** (and linked student/faculty rows). Options:
  1. **Export/import** data from your local Postgres (pg_dump / restore to Neon), or  
  2. **Re-create** admin via your app’s admin flows if you have them, or  
  3. Insert a test admin manually in Neon SQL Editor (match how `User` entity stores `role`, e.g. `ROLE_ADMIN` — check your local DB).

If deploy logs show **Connection refused** or **SSL** errors, double-check `?sslmode=require` on the JDBC URL.

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| Build fails “Dockerfile not found” | Wrong **Root Directory** — point to folder that contains `Dockerfile`. |
| `password authentication failed` | Wrong `DATABASE_USERNAME` / `DATABASE_PASSWORD` or JDBC URL. |
| `Connection timed out` | Neon project paused — open Neon dashboard to wake it; retry deploy. |
| 502 / app crash on start | Render **Logs** tab — often missing env var or invalid JDBC URL. |
| Very slow first page | Normal on free Render + waking Neon. |
| Profile photos disappear | Expected on free Render (`/tmp`); use local demo or add S3 later. |

---

## Local test (same as production env)

From `College_ERP_Fully_Java_SpringBoot`:

```bash
docker build -t college-erp .
docker run --rm -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DATABASE_JDBC_URL='jdbc:postgresql://YOUR_HOST/neondb?sslmode=require' \
  -e DATABASE_USERNAME='your_neon_user' \
  -e DATABASE_PASSWORD='your_neon_password' \
  college-erp
```

---

## Not Vercel

This app does **not** run on Vercel. Use your Render URL as the public link.

---

## Security (before sharing widely)

- Do not commit real DB passwords; use Render env vars only in prod.
- Change default demo passwords after go-live.
- Rotate Neon password if it was ever exposed in git.
