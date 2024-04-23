var ctx = window.location.pathname;

function navigateTo(url) {
	console.log(ctx);
	console.log(url);
	window.location.replace(url);
}

function avoidEnter(event) {
	if (event.key == 'Enter') return false;
	return true;
}
