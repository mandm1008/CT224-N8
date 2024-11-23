function add_user()
{
    document.them_user.style.display = "block";
    document.them_user.username.value = '';
    document.them_user.password.value = '';
    document.them_user.email.value = '';
    
    var them_hide = document.getElementById("them_hide_button");
    them_hide.style.display = "block";
    
    var form_them = document.getElementById("them_user");
    form_them.action = "./them_user";
}

function edit_user()
{
    var i = event.target.closest('tr').rowIndex;
    var ta = document.getElementById('song_table_id');
    var user_id = ta.rows[i].cells[0].innerText;
    var username = ta.rows[i].cells[1].innerText;
    var password = ta.rows[i].cells[2].innerText;
    var email = ta.rows[i].cells[3].innerText;
    
    document.them_user.user_id.value = user_id;
    document.them_user.username.value = username;
    document.them_user.password.value = password;
    document.them_user.email.value = email;
    
    document.them_user.style.display = "block";
    var form_change_action = document.getElementById("them_user");
    form_change_action.action = "./sua_user";
}

function delete_user()
{
    var i = event.target.closest('tr').rowIndex;
    var ta = document.getElementById('song_table_id');
    var user_id = ta.rows[i].cells[0].innerText;
    document.xoa_user.user_id.value = user_id;
    var xu = document.getElementById("xoa_user");
    xu.submit();
}

document.addEventListener('DOMContentLoaded', function () {
  const content = document.querySelector('.content'); 
  const itemsPerPage = 5;
  let currentPage = 0;
  const items = Array.from(content.getElementsByTagName('tr')).slice(1);

function showPage(page) {
  const startIndex = page * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  items.forEach((item, index) => {
    item.classList.toggle('hidden', index < startIndex || index >= endIndex);
  });
  updateActiveButtonStates();
}

function createPageButtons() {
  const totalPages = Math.ceil(items.length / itemsPerPage);
  const paginationContainer = document.createElement('div');
  const paginationDiv = document.body.appendChild(paginationContainer);
  paginationContainer.classList.add('pagination');

  // Add page buttons
  for (let i = 0; i < totalPages; i++) {
    const pageButton = document.createElement('button');
    pageButton.textContent = i + 1;
    pageButton.addEventListener('click', () => {
      currentPage = i;
      showPage(currentPage);
      updateActiveButtonStates();
    });

      content.appendChild(paginationContainer);
      paginationDiv.appendChild(pageButton);
    }
}

function updateActiveButtonStates() {
  const pageButtons = document.querySelectorAll('.pagination button');
  pageButtons.forEach((button, index) => {
    if (index === currentPage) {
      button.classList.add('active');
    } else {
      button.classList.remove('active');
    }
  });
}

  createPageButtons(); // Call this function to create the page buttons initially
  showPage(currentPage);
});




