// onSubmit submits the form after hCaptcha is solved
function onSubmit(token) {
	document.getElementsByTagName('form')[0].submit();
}

// validate executes the hCaptcha if there is no error
function validate(event, errors) {
	event.preventDefault();
	let err = document.getElementsByClassName('err')[document.getElementsByClassName('err').length-1];
	err.innerText = '';
	// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/some
	const hasErr = (element) => element == true;
	if (!errors.some(hasErr) & errors.length != 0) {
		hcaptcha.execute();
	}
}

// printErr prints the error message along side the input field
function printErr(element, bool) {
	if(bool) {
		element.parentNode.nextElementSibling.innerText = '';
	} else {
		element.parentNode.nextElementSibling.innerText = element.parentNode.previousElementSibling.innerText + ' is not valid';
	}
	return !bool;
}

function usernameIsValid(username) {
	return /^[0-9a-zA-Z_.-]+$/.test(username);
}

function passwordIsValid(password) {
	return /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-/]).{8,}$/.test(password);
}
function emailisvalid(email) {
	return /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(email);
}

window.addEventListener('load', function () {
	let menuOpen = false;
	let errors = [];
	const hasErr = (element) => element == true;
	let err = document.getElementsByClassName('err')[document.getElementsByClassName('err').length-1];

	document.querySelector('nav details summary').addEventListener('click', function () {
		menuOpen = !menuOpen;
		if (menuOpen) {
			document.querySelector('main').style.marginTop = '6em';
		} else {
			document.querySelector('main').style.marginTop = '4em';
		}
	})
	if (location.pathname == '/') {
		let userIcon = document.querySelector('nav > ul li:last-child a');
		fetch('/includes/api.php?username')
			.then(response => response.text())
			.then(data => {
				if(data != '') {
					userIcon.innerText = data;
				}
			});
		let swiper = new Swiper("#explore", {
			mousewheel: true,
			slidesPerView: "auto",
			spaceBetween: 30,
			scrollbar: {
				el: ".swiper-scrollbar",
			},
			autoplay: {
				delay: 5000,
			},
		});
	} else {
		let submit = document.querySelector('form button');
		submit.addEventListener('click', function (event) {
			// console.log(errors);
			validate(event, errors);
		});
		if(location.pathname.includes('login')) {
			submit.addEventListener('mouseenter', function() {
				if (!document.getElementsByName('username')[0].value || !document.getElementsByName('password')[0].value) {
					err.innerText = 'Please fill in all fields';
					errors[0] = true;
				} else {
					err.innerText = '';
					errors[0] = false;
				}
			});
		} else if (location.pathname.includes('signup')) {
			let username = document.getElementsByName("username")[0];
			let password = document.getElementsByName("password")[0];
			let confirmed = document.getElementsByName("password")[1];
			let email = document.getElementsByName("email")[0];
			submit.addEventListener('mouseenter', function() {
				if (!username.value || !password.value || !confirmed.value || !email.value) {
					err.innerText = 'Please fill in all fields';
					errors[4] = true;
				} else {
					err.innerText = '';
					errors[4] = false;
				}
			});

			username.addEventListener("blur", () => { //add event listener for blur
				
				if(usernameIsValid(username.value)){
					fetch('includes/api.php?username=' + username.value) //fetch the data from the api
						//.then(response => response.json())
						.then(response => response.text())
						.then(response => {
							if (response == 'true') { // if the response is false or error, show the error message
								username.parentNode.nextElementSibling.innerText = 'Username already taken';
								errors[0] = true;
							}
							else {
								username.parentNode.nextElementSibling.innerText = '';
								errors[0] = false;
							}
						}
						);	
				}else{
					username.parentNode.nextElementSibling.innerText = 'Username is not valid';
					errors[0] = true;
				}
			});
			email.addEventListener("blur", () => { 
				if(emailisvalid(email.value)){
					fetch('includes/api.php?email=' + email.value) //fetch the data from the api
						//.then(response => response.json())
						.then(response => response.text())
						.then(response => {
							if (response == 'true') { // if the response is false or error, show the error message
								email.parentNode.nextElementSibling.innerText = 'email already registered';
								errors[1] = true;
							}
							else {
								email.parentNode.nextElementSibling.innerText = '';
								errors[1] = false;
							}
						}
						);	
				}else{
					email.parentNode.nextElementSibling.innerText = 'email is not valid';
					errors[1] = true;
				}
			});
			//test if the password is valid
			password.addEventListener("blur", () => {
				errors[2] = printErr(password, passwordIsValid(password.value));
			});
			//test if the confirmed password is match
			confirmed.addEventListener("blur", () => {
				errors[3] = printErr(confirmed, password.value == confirmed.value)
			});
		}
	} 
});