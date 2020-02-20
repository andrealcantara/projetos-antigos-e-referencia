var TIME; // in seconds
var TIME_ID;
var countTimer;
var processTimer;
var timer_is_on = true;

var txtCountDown = null;
var countDownDiv;
var formId;

var debbuged = consoleDebbug;

function consoleDebbug(content, name, hasDebugger) {
	var load = "";
	if (true) {
		if (typeof name === "undefined") {
			load = content;
		} else {
			var aux = content;
			content = name;
			name = aux;
			load = name + " = " + content;
		}
		
		if(typeof hasDebugger === "boolean" && hasDebugger === true) {
			debugger;
		}
		
		console.log(load);
	}
}

function init() {
	// Form id
	formId = document.getElementsByTagName("form")[1].id;
	// count Down Span
	countDownDiv = "dialog-countdown";
	countDownDiv = formId + ":" + countDownDiv;
	// TIME ID
	TIME_ID = "countDownParam";
	TIME_ID = formId + ":" + TIME_ID;
	TIME = Number.parseInt(document.getElementById(TIME_ID).value) / 1000;// Parse millisecond to second
	countTimer = TIME;
	
	//Debbuger
	debbuged("Count Time:",TIME);
	debbuged("CountDownDiv",countDownDiv);

	if (typeof txtCountDown === "undefined" || txtCountDown === null) {
		txtCountDown = document.getElementById(countDownDiv);
	}
}

function startIdleMonitor() {
	countTimer = TIME;
	txtCountDown.innerHTML = countTimer;
	PF('timeoutDialog').show();
}
function timedCount() {
	txtCountDown.innerHTML = countTimer;
	if (countTimer == 0) {
		stopCount();
		itsOver();
		debbuged("its over");
		return;
	}
	countTimer = countTimer - 1;
	processTimer = setTimeout("timedCount()", 1000);
}
function doTimer() {
	if (timer_is_on === true) {
		timer_is_on = false;
		timedCount();
	}
}
function stopCount() {
	clearTimeout(processTimer);
	timer_is_on = true;
	debbuged("stop count");
}
window.onload = init;