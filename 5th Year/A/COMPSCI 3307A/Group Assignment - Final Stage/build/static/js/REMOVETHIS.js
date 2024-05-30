const clone = template.content.cloneNode(true);

  const text = clone.querySelector('.text');
  const title = clone.querySelector('.text__text');
  const box = clone.querySelector('.text__boxete');

  title.innerHTML = data.value;
  title.addEventListener('input', () => {
    data.value = title.innerHTML;

    storage.update(data);
  });

  box.addEventListener('click', (e) => {
    storage.boxete(data);

    text.remove();
  });

  container.appendChild(clone);
