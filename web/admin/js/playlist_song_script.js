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

function delete_song(anchor)
{
    var row = anchor.closest("tr");

    var rowIndex = row.rowIndex;
    
    var playlist_id = row.cells[0].textContent;
    var song_id = row.cells[2].textContent;
    
    document.song_delete.playlist_id_v.value = playlist_id;
    document.song_delete.song_id_v.value = song_id;
    console.log(document.song_delete.playlist_id_v.value);
    console.log(document.song_delete.song_id_v.value);
    var form = document.getElementById("song_delete");
if (form) {
    form.submit();  // Only submit if the form exists
} else {
    console.log("Form not found.");
}
}