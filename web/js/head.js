/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const searchButton = document.querySelector('#searchButton');
const searchInput = document.querySelector('#searchInput');

searchButton.addEventListener("click", function (e) {
    if (searchInput.value.length > 0)
        window.location = contextPath + "/search.jsp?searchkey=" + searchInput.value;
});
