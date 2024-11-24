document.addEventListener("DOMContentLoaded", () => {
    const checkboxes = document.querySelectorAll(".selectedSongs");

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener("change", () => {
            const value = checkbox.value;
            const isChecked = checkbox.checked;

            // Send AJAX request
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "Playlist_add_check", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const response = xhr.responseText.trim(); // Get the plain text response

                    if (response === "allowed") {
                        if (isChecked) {
                            alert(`Song is allowed.`);
                            
                        }
                    } else {
                        alert(`Song is not allowed.`);
                        checkbox.checked = false; // Uncheck the checkbox if not allowed
                    }
                    console.log(response);
                }
            };

            // Send the checkbox state and value to the servlet
            xhr.send(`songId=${value}&checked=${isChecked}`);
            console.log(value);
            console.log(isChecked);
        });
    });
});


function tim_playlist_f()
{
    var tim_value = document.getElementById("search_playlist").value;
    
    console.log(document.getElementById("search_playlist").value);
    var tim_op = document.getElementById("tim_op_playlist");
    if(tim_op.value === "tim_op_select")
    {
        alert("Chọn giá trị tìm kiếm");
    }
    else
    {
        document.tim_playlist.tim_v.value = tim_value;
        document.tim_playlist.tim_w.value = tim_op.value;
        document.tim_playlist.sop.value = "playlists";
        document.getElementById("tim_playlist").submit();
    }
}

function tim_song()
{
    var tim_value = document.getElementById("search_song").value;
    console.log(tim_value);
    var tim_op = document.getElementById("tim_op");
    if(tim_op.value === "tim_op_select")
    {
        alert("Chọn giá trị tìm kiếm");
    }
    else
    {
        document.tim_playlist.tim_v.value = tim_value;
        document.tim_playlist.tim_w.value = tim_op.value;
        document.tim_playlist.sop.value = "songs";
        document.getElementById("tim_playlist").submit();
    }
    
    
}

function reload_playlist_sc()
{
        document.tim_playlist.sop.value = "playlists";
        var tim_pl = document.getElementById("tim_playlist");
        tim_pl.action = "./Reload_Playlist";
        tim_pl.submit();
}

function reload_song_sc()
{
        document.tim_playlist.sop.value = "songs";
        var tim_pl = document.getElementById("tim_playlist");
        tim_pl.action = "./Reload_Playlist";
        tim_pl.submit();
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



document.addEventListener('DOMContentLoaded', function () {
  const contentSong = document.querySelector('.content_song'); // Đổi tên biến để áp dụng cho content_song
  const itemsPerPageSong = 5; // Số mục mỗi trang
  let currentPageSong = 0; // Biến trang hiện tại
  const itemsSong = Array.from(contentSong.getElementsByTagName('tr')).slice(1); // Lấy các hàng (bỏ tiêu đề bảng)

  function showPageSong(page) {
    const startIndex = page * itemsPerPageSong;
    const endIndex = startIndex + itemsPerPageSong;
    itemsSong.forEach((item, index) => {
      item.classList.toggle('hidden', index < startIndex || index >= endIndex);
    });
    updateActiveButtonStatesSong();
  }

  function createPageButtonsSong() {
    const totalPagesSong = Math.ceil(itemsSong.length / itemsPerPageSong);
    const paginationContainerSong = document.createElement('div');
    const paginationDivSong = document.body.appendChild(paginationContainerSong);
    paginationContainerSong.classList.add('pagination_song'); // Thay đổi class để không xung đột

    // Add page buttons
    for (let i = 0; i < totalPagesSong; i++) {
      const pageButtonSong = document.createElement('button');
      pageButtonSong.textContent = i + 1;
      pageButtonSong.addEventListener('click', () => {
        currentPageSong = i;
        showPageSong(currentPageSong);
        updateActiveButtonStatesSong();
      });

      contentSong.appendChild(paginationContainerSong);
      paginationDivSong.appendChild(pageButtonSong);
    }
  }

  function updateActiveButtonStatesSong() {
    const pageButtonsSong = document.querySelectorAll('.pagination_song button');
    pageButtonsSong.forEach((button, index) => {
      if (index === currentPageSong) {
        button.classList.add('active');
      } else {
        button.classList.remove('active');
      }
    });
  }

  createPageButtonsSong(); // Call this function to create the page buttons initially
  showPageSong(currentPageSong);
});


function send_servlet()
{
        var inputString = "hello";

        fetch('Playlist_Song', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: 'inputString=' + encodeURIComponent(inputString)
        })
        .then(response => response.json())  // Dữ liệu trả về dưới dạng JSON
        .then(data => {
            // Get the table body element
            const tableBody = document.getElementById('tableBody_playlist_song');
            
            // Clear the existing rows from the table body
            tableBody.innerHTML = '';

            // Get the lists from the response
            const list1 = data.playlist_id;  // Playlist IDs
            const list2 = data.song_id;  // Song IDs
            const list3 = data.title;  // Titles

            // Assuming all lists have the same length, iterate through them
            for (let i = 0; i < list1.length; i++) {
                // Create a new row for each set of data
                const row = document.createElement('tr');
                
                // Create and append Playlist ID cell
                const cell1 = document.createElement('td');
                cell1.textContent = list1[i];  // Playlist ID
                row.appendChild(cell1);

                // Create and append Title cell
                const cell2 = document.createElement('td');
                cell2.textContent = list3[i];  // Title
                row.appendChild(cell2);

                // Create and append hidden Song ID cell
                const cell3 = document.createElement('td');
                cell3.textContent = list2[i];  // Song ID
                cell3.style.display = 'none';  // Hide the Song ID cell
                row.appendChild(cell3);

                // Create and append Edit button
                const cell4 = document.createElement('td');
                const button = document.createElement('button');
                button.textContent = 'Edit';
                button.onclick = function() { 
                    // You can handle the edit action here
                    console.log('Edit button clicked for: ' + list1[i]);
                };
                cell4.appendChild(button);
                row.appendChild(cell4);

                // Append the row to the table body
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error('Error:', error));
}


document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('.playlist_check');

    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', (event) => {
            const clickedCheckbox = event.target;

            // Uncheck all other checkboxes
            checkboxes.forEach((cb) => {
                if (cb !== clickedCheckbox) {
                    cb.checked = false;
                }
            });
            document.getElementById("playlist_id_v").value = clickedCheckbox.value;
            
        });
    });
});


function add_to_playlist()
{
    var playlist_name = document.getElementById("txt_add_playlist").value;
    document.add_song_playlist.name.value = playlist_name;
    document.getElementById("add_song_playlist").submit();
}

function checkSongAndAdd(button) {
            // Get the string from the input field
            var row = button.closest("tr");

            var rowIndex = row.rowIndex;

            var song_id = row.cells[0].textContent;
            console.log(song_id);
            var playlist_id = document.getElementById("playlist_id_v").value;
            console.log(playlist_id);
            // Validate the input before sending (optional)
            if(!song_id || ! playlist_id) {
                alert("Input cannot be empty.");
                return;
            }

            // Send the string to the servlet
            fetch("Check_And_Add", {
                method: "POST",
                headers: {
                    "Content-Type": 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                body: `song_id=${encodeURIComponent(song_id)}&playlist_id=${encodeURIComponent(playlist_id)}`
            })
            .then(response => response.text())
            .then(data => {
                if (data === "allowed") 
            {
                alert("Success!");
            }   
            else 
        {
                alert("Song is not allowed.");
        }
            }
                    )
            .catch(error => {
                console.error("Error:", error);
                alert("Lỗi");
            });
        }

function sua_playlist_sc(button)
{
    // Get the row element
    var row = button.closest("tr");

    var rowIndex = row.rowIndex;

    var playlist_id = row.cells[0].textContent;
    var name = row.cells[1].textContent;
    
    
    document.sua_xoa_playlist.playlist_id_v_update.value = playlist_id;
    document.sua_xoa_playlist.name_update.value = name;
    var form = document.getElementById("sua_xoa_playlist");
    form.action = "./sua_playlist";
    form.style.display = "block";
}

function xoa_playlist_sc(button)
{
    var row = button.closest("tr");

    var rowIndex = row.rowIndex;

    var playlist_id = row.cells[0].textContent;
    
    document.sua_xoa_playlist.playlist_id_v_update.value = playlist_id;
    var form = document.getElementById("sua_xoa_playlist");
    form.action = "./xoa_playlist";
    form.submit();
}

function send_to_pl_detail(button)
{
    var row = button.closest("tr");

    var rowIndex = row.rowIndex;

    var playlist_id = row.cells[0].textContent;
    var name = row.cells[1].textContent;
    var form_detail = document.getElementById("Playlist_detail");
    document.Playlist_detail.playlist_id_detail_v.value = playlist_id;
    document.Playlist_detail.name_detail_v.value = name;
    form_detail.submit();
}