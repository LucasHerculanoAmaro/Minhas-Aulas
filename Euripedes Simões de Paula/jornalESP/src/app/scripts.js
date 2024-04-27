const daysContainer = document.querySelector(".days");
const day = document.querySelector(".days .day");
const nextBtn = document.querySelector(".next-btn");
const prevBtn = document.querySelector(".prev-btn");
const todayBtn = document.querySelector(".today-btn");
const month = document.querySelector(".month");
const event = document.querySelector(".event");

const months = [
  "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
  "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
];

const days = ["dom","seg","ter","qua","qui","sex","sab"];

const date = new Date();
let currentMonth = date.getMonth();
let currentYear = date.getFullYear();

function rendercalendar() {
  const firstDay = new Date( currentYear, currentMonth, 1 );
  const lastDay = new Date( currentYear, currentMonth + 1, 0 );
  const lastDayIndex = lastDay.getDay();
  const lastDayDate = lastDay.getDate();
  const prevLastDay = new Date( currentYear, currentMonth, 0 );
  const prevLastDayDate = prevLastDay.getDate();
  const nextDays = 7 - lastDayIndex - 1;

  month.innerHTML = `${months[currentMonth]} ${currentYear}`;
  
  let days = "";

  for(let x = firstDay.getDay(); x > 0; x--){
    days += `<div class="day prev">${prevLastDayDate - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDayDate; i++) {
    if(i == date.getDate() ){
      days += `<div class="day today">${i}</div>`;}
      else{
            days += `<div class="day">${i}</div>`;}
    }


   for (let j = 1; j <= nextDays; j++){
    days += `<div class="day next">${j}</div>`;
  }
  
  daysContainer.innerHTML = days;}

rendercalendar();

function next_click() {
  
  currentMonth ++;
  if(currentMonth > 11){
    currentMonth = 0;
    currentYear ++;
  }
  month.innerHTML = `${months[currentMonth]} ${currentYear}`;

  const firstDay = new Date( currentYear, currentMonth, 1 );
  const lastDay = new Date( currentYear, currentMonth + 1, 0 );
  const lastDayIndex = lastDay.getDay();
  const lastDayDate = lastDay.getDate();
  const prevLastDay = new Date( currentYear, currentMonth, 0 );
  const prevLastDayDate = prevLastDay.getDate();
  const nextDays = 7 - lastDayIndex - 1;
  
  let days = "";

  for(let x = firstDay.getDay(); x > 0; x--){
    days += `<div class="day prev">${prevLastDayDate - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDayDate; i++) {
    if(i == date.getDate()){
      days += `<div class="day today">${i}</div>`;}
    else{
            days += `<div class="day">${i}</div>`;}
    }
  
   for (let j = 1; j <= nextDays; j++){
    days += `<div class="day next">${j}</div>`;
  }

  daysContainer.innerHTML = days;
}

function prev_click() {

  currentMonth --;
  if(currentMonth < 1){
    currentMonth = 11;
    currentYear --;
  }
  month.innerHTML = `${months[currentMonth]} ${currentYear}`;

  const firstDay = new Date( currentYear, currentMonth, 1 );
  const lastDay = new Date( currentYear, currentMonth + 1, 0 );
  const lastDayIndex = lastDay.getDay();
  const lastDayDate = lastDay.getDate();
  const prevLastDay = new Date( currentYear, currentMonth, 0 );
  const prevLastDayDate = prevLastDay.getDate();
  const nextDays = 7 - lastDayIndex - 1;

  let days = "";

  for(let x = firstDay.getDay(); x > 0; x--){
    days += `<div class="day prev">${prevLastDayDate - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDayDate; i++) {
    if(i == date.getDate()){
      days += `<div class="day today">${i}</div>`;}
    else{
            days += `<div class="day">${i}</div>`;}
    }

   for (let j = 1; j <= nextDays; j++){
    days += `<div class="day next">${j}</div>`;
  }

  daysContainer.innerHTML = days;
}

function today_click() {

  currentMonth = date.getMonth();
  currentYear = date.getFullYear();
  
  month.innerHTML = `${months[currentMonth]} ${currentYear}`;

  const firstDay = new Date( currentYear, currentMonth, 1 );
  const lastDay = new Date( currentYear, currentMonth + 1, 0 );
  const lastDayIndex = lastDay.getDay();
  const lastDayDate = lastDay.getDate();
  const prevLastDay = new Date( currentYear, currentMonth, 0 );
  const prevLastDayDate = prevLastDay.getDate();
  const nextDays = 7 - lastDayIndex - 1;

  let days = "";

  for(let x = firstDay.getDay(); x > 0; x--){
    days += `<div class="day prev">${prevLastDayDate - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDayDate; i++) {
    if(i == date.getDate()){
      days += `<div class="day today">${i}</div>`;}
      else{
            days += `<div class="day">${i}</div>`;}
  }

   for (let j = 1; j <= nextDays; j++){
    days += `<div class="day next">${j}</div>`;
  
  daysContainer.innerHTML = days;}}