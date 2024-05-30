const pjax = (url) => {
    // Store the current URL as the previous page
    localStorage.setItem('previousPage', location.href);

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');

            // Update the head - specifically look for changes in stylesheets
            const newHead = doc.head;
            const oldHead = document.head;

            // Remove any existing stylesheets
            Array.from(oldHead.querySelectorAll('link[rel="stylesheet"], style')).forEach(el => el.remove());

            // Append new stylesheets from the new page
            Array.from(newHead.querySelectorAll('link[rel="stylesheet"], style')).forEach(newStyle => {
                oldHead.appendChild(newStyle);
            });

            // Replace the entire body's content
            const oldBody = document.body;
            const newBody = doc.body;
            oldBody.innerHTML = newBody.innerHTML;

            // Update the browser's URL and history
            history.pushState({}, '', url);

            // Update the title of the page
            document.title = doc.title;

            // Store the new URL as the next page
            localStorage.setItem('nextPage', url);

            // Emit the onload event
            window.dispatchEvent(new Event('load'));
        })
        .catch(error => {
            console.error('Failed to load page: ', error);
        });
};

window.onpopstate = () => {
    pjax(location.pathname);
};
