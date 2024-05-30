function edit() {
	let hiddenForm = document.getElementsByTagName('form')[0];
	let input = hiddenForm.querySelector("input");
	let info = event.target.previousElementSibling;
	input.name = info.name;
	input.value = info.value;
	hiddenForm.submit();
}
function editList() {
	let hiddenForm = document.getElementsByTagName('form')[0];
	let input = hiddenForm.querySelector("input");
	let listId = event.target.parentNode.firstElementChild.name;
	input.name = "edit";
	input.value = listId;
	hiddenForm.submit();
}
function deleteList() {
	let hiddenForm = document.getElementsByTagName('form')[0];
	let input = hiddenForm.querySelector("input");
	let listId = event.target.parentNode.firstElementChild.name;
	input.name = 'delete';
	input.value = listId;
	hiddenForm.submit();
}
function deleteUser() {
	let hiddenForm = document.getElementsByTagName('form')[0];
	let input = hiddenForm.querySelector("input");
	input.name = 'delete';
	hiddenForm.submit();
}
function update() {
	let hiddenForm = document.getElementsByTagName('form')[0];
	let input = hiddenForm.querySelector("input");
	let info = document.querySelector('.list > div > ul').innerHTML;
	input.name = "update";
	input.value = info;
	hiddenForm.submit();
}
window.addEventListener('load', function () {
	let userIcon = document.querySelector("nav li:nth-child(4) > a");
	let dropDown = document.getElementsByClassName("dropdown")[0];
	let menuOpen = false;
	let dropDownOpen = false;

	if(userIcon.innerText.length < 4) {
		userIcon.previousElementSibling.style.width = "6em";
	}

	document.querySelector('nav details summary').addEventListener('click', function () {
		menuOpen = !menuOpen;
		if (menuOpen) {
			document.querySelector('main').style.marginTop = '6em';
		} else {
			document.querySelector('main').style.marginTop = '4em';
		}
	})

	userIcon.addEventListener('click', function () {
		dropDown.style.display = dropDownOpen ? "none" : "block";
		dropDownOpen = !dropDownOpen;
	});
	
	if (location.pathname.includes('list')) {
		let editButton = document.getElementsByName('edit');
		let deleteButton = document.getElementsByName('delete');
		editButton.forEach(function (button) {
			button.addEventListener('click', function() {
				event.preventDefault();
				editList();
			});
		});
		deleteButton.forEach(function (button) {
			button.addEventListener('click', function() {
				event.preventDefault();
				deleteList();
			});
		});
	}
});