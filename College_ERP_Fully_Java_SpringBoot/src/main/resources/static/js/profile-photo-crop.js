/**
 * Square profile photo crop + upload (Cropper.js).
 * config: { fileInputId, uploadUrl, accentColor? }
 */
function initProfilePhotoCrop(config) {
    var fileInput = document.getElementById(config.fileInputId);
    if (!fileInput || typeof Cropper === 'undefined') {
        return;
    }

    var accent = config.accentColor || '#2d3436';
    var modal = document.getElementById('profileCropModal');
    if (!modal) {
        modal = document.createElement('div');
        modal.id = 'profileCropModal';
        modal.className = 'crop-modal';
        modal.innerHTML =
            '<div class="crop-dialog" role="dialog" aria-modal="true" aria-labelledby="cropTitle">' +
            '<h3 id="cropTitle">Crop profile photo</h3>' +
            '<p>Drag to position. The photo will be saved as a square.</p>' +
            '<div class="crop-stage"><img id="profileCropImage" alt="Crop preview"></div>' +
            '<div class="crop-actions">' +
            '<button type="button" class="crop-btn-cancel" id="profileCropCancel">Cancel</button>' +
            '<button type="button" class="crop-btn-save" id="profileCropSave">Save photo</button>' +
            '</div></div>';
        document.body.appendChild(modal);
    }

    var cropImg = document.getElementById('profileCropImage');
    var saveBtn = document.getElementById('profileCropSave');
    saveBtn.style.background = accent;

    var cropper = null;
    var objectUrl = null;

    function closeModal() {
        modal.classList.remove('open');
        if (cropper) {
            cropper.destroy();
            cropper = null;
        }
        if (objectUrl) {
            URL.revokeObjectURL(objectUrl);
            objectUrl = null;
        }
        fileInput.value = '';
        saveBtn.disabled = false;
        saveBtn.textContent = 'Save photo';
    }

    document.getElementById('profileCropCancel').onclick = closeModal;
    modal.addEventListener('click', function (e) {
        if (e.target === modal) closeModal();
    });

    fileInput.addEventListener('change', function () {
        var file = fileInput.files && fileInput.files[0];
        if (!file) return;

        if (!/^image\//.test(file.type)) {
            alert('Please choose an image file.');
            fileInput.value = '';
            return;
        }

        objectUrl = URL.createObjectURL(file);
        cropImg.src = objectUrl;
        modal.classList.add('open');

        if (cropper) cropper.destroy();
        cropper = new Cropper(cropImg, {
            aspectRatio: 1,
            viewMode: 1,
            dragMode: 'move',
            autoCropArea: 1,
            responsive: true,
            background: false
        });
    });

    saveBtn.onclick = function () {
        if (!cropper) return;
        saveBtn.disabled = true;
        saveBtn.textContent = 'Uploading…';

        var canvas = cropper.getCroppedCanvas({
            width: 512,
            height: 512,
            imageSmoothingEnabled: true,
            imageSmoothingQuality: 'high'
        });

        if (!canvas) {
            alert('Could not crop this image.');
            saveBtn.disabled = false;
            saveBtn.textContent = 'Save photo';
            return;
        }

        canvas.toBlob(function (blob) {
            if (!blob) {
                alert('Could not process image.');
                saveBtn.disabled = false;
                saveBtn.textContent = 'Save photo';
                return;
            }

            var fd = new FormData();
            fd.append('photo', blob, 'profile.jpg');

            fetch(config.uploadUrl, { method: 'POST', body: fd, credentials: 'same-origin' })
                .then(function (res) {
                    if (res.redirected) {
                        window.location.href = res.url;
                        return;
                    }
                    if (res.ok) {
                        window.location.href = window.location.pathname + '?photo=success';
                    } else {
                        throw new Error('upload failed');
                    }
                })
                .catch(function () {
                    alert('Upload failed. Try again.');
                    saveBtn.disabled = false;
                    saveBtn.textContent = 'Save photo';
                });
        }, 'image/jpeg', 0.92);
    };
}
