document.getElementById('detectBtn')?.addEventListener('click', detectMood);

async function detectMood() {
    const inputEl = document.getElementById('moodInput');
    const text = inputEl ? inputEl.value.trim() : '';
    if (!text) { alert('Please enter how you feel.'); return; }

    try {
        const res = await fetch('/api/mood/detect', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ text })
        });

        if (!res.ok) {
            alert('Server error: ' + res.status);
            return;
        }

        const data = await res.json();
        showResult(data);
    } catch (err) {
        console.error(err);
        alert('Network or server error. Check console.');
    }
}

function showResult(data) {
    // elements must exist in your index.html
    const emojiWrap = document.getElementById('emojiWrap');
    const moodLabel = document.getElementById('moodLabel');
    const quoteBlock = document.getElementById('quoteBlock');
    const songBlock = document.getElementById('songBlock');

    if (emojiWrap) emojiWrap.innerHTML = `<span style="font-size:48px">${data.emoji || '🙂'}</span>`;
    if (moodLabel) moodLabel.innerText = (data.mood || '').toUpperCase();

    if (quoteBlock) {
        if (data.quote) {
            quoteBlock.innerHTML = `<h3>Quote</h3><p>"${data.quote}"</p><p style="font-style:italic">- ${data.author || 'Unknown'}</p>`;
        } else {
            quoteBlock.innerHTML = '';
        }
    }

    if (songBlock) {
        if (data.songTitle && data.youtubeLink) {
            songBlock.innerHTML = `<h3>Song</h3><p><a href="${data.youtubeLink}" target="_blank" rel="noopener">${data.songTitle}</a></p>`;
        } else {
            songBlock.innerHTML = '';
        }
    }
}
