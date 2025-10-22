const avatarInput = document.getElementById('avatarInput');
    const avatarPreview = document.getElementById('avatarPreview');
    const removeBtn = document.getElementById('removeAvatarBtn');
    const profileForm = document.getElementById('profileForm');
    const discardBtn = document.getElementById('discardBtn');
    const displayName = document.getElementById('displayName');
    const displayHandle = document.getElementById('displayHandle');

    // Preview selected avatar
    avatarInput.addEventListener('change', function(e){
      const file = e.target.files[0];
      if (!file) return;
      const reader = new FileReader();
      reader.onload = function(ev){ avatarPreview.src = ev.target.result; };
      reader.readAsDataURL(file);
    });

    // Remove avatar - revert to placeholder image
    removeBtn.addEventListener('click', function(){
      avatarInput.value = '';
      avatarPreview.src = 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=800&q=60';
    });

    // Discard changes: reset form and preview to initial values
    discardBtn.addEventListener('click', function(){
      if(!profileForm) return;
      profileForm.reset();
      // optionally refresh preview and quick meta - if you have server values, re-load them instead
      avatarPreview.src = 'https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=800&q=60';
      displayName.textContent = document.getElementById('fullName')?.value || 'Jane Doe';
      displayHandle.textContent = '@' + (document.getElementById('username')?.value || 'janedoe');
    });

    // keep quick meta updated on input
    const fullNameInput = document.getElementById('fullName');
    const usernameInput = document.getElementById('username');
    if(fullNameInput){
      fullNameInput.addEventListener('input', e => displayName.textContent = e.target.value || 'Jane Doe');
    }
    if(usernameInput){
      usernameInput.addEventListener('input', e => displayHandle.textContent = '@' + (e.target.value || 'janedoe'));
    }

    // Optional: simple password confirmation check (client-side)
    const passForm = document.getElementById('passwordForm');
    if(passForm){
      passForm.addEventListener('submit', function(e){
        const n = document.getElementById('newPassword').value;
        const c = document.getElementById('confirmPassword').value;
        if(n !== c){
          e.preventDefault();
          alert('New password and confirmation do not match.');
        }
      });
    }