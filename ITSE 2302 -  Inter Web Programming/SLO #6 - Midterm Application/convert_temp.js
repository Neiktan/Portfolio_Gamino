"use strict";
const $ = selector => document.querySelector(selector);

/*********************
*  helper functions  *
**********************/
const calculateCelsius = temp => (temp - 32) * 5 / 9;
const calculateFahrenheit = temp => temp * 9 / 5 + 32;

const toggleDisplay = (label1Text, label2Text) => {
    // Change labels based on the selected radio button
    $("#degree_label_1").textContent = label1Text;
    $("#degree_label_2").textContent = label2Text;

    // Clear the disabled input field
    $("#degrees_computed").value = "";
    $("#degrees_entered").value = "";
};

/****************************
*  event handler functions  *
*****************************/
const convertTemp = () => {
    const inputValue = $("#degrees_entered").value.trim();
    let result;

    // Check if the input is a valid number
    if (isNaN(inputValue) || inputValue === "") {
        $("#message").textContent = "You must enter a valid number for degrees";
        return;
    }
	//Erase the message after a correct input
	$("#message").textContent = "";

    // Perform conversion based on the selected radio button
    if ($("#to_celsius").checked) {
        result = calculateCelsius(Number(inputValue)).toFixed(0);
        $("#degrees_computed").value = result + " °C";
    } else if ($("#to_fahrenheit").checked) {
        result = calculateFahrenheit(Number(inputValue)).toFixed(0);
        $("#degrees_computed").value = result + " °F";
    }
    
    // Set focus back to the input field for convenience
    $("#degrees_entered").focus().select();
};

const toCelsius = () => toggleDisplay("Enter F degrees:", "Degrees Celsius:");
const toFahrenheit = () => toggleDisplay("Enter C degrees:", "Degrees Fahrenheit:");

document.addEventListener("DOMContentLoaded", () => {
	$("#convert").addEventListener("click", convertTemp);
    $("#to_celsius").addEventListener("click", toCelsius);
    $("#to_fahrenheit").addEventListener("click", toFahrenheit);
});