

//Thêm Data từ bảng vào Form Thêm
function them_show()
{
    document.them.style.display = "block";
    var PT_show = document.querySelectorAll(".them_song_id");
    PT_show.forEach(PT_show => {
                   PT_show.style.display = "none";
            });
    document.them.title.value = '';
    document.them.artist_id.value = '';
    document.them.link_them.value = '';
    document.them.lof_them.value = "0";
    document.getElementById("options_them").value = "option_select";
    var them_hide = document.getElementById("them_hide_button");
    them_hide.style.display = "block";
    var form = document.getElementById("them");
    form.action = "./them";
    document.getElementById("form_title").innerText = "THÊM BÀI HÁT";
}

                                            //Giấu Form Thêm
function them_hide()
{
        document.them.style.display = "none";
        document.getElementById("them_hide_button").style.display = "none";
}

                                            //Chọn File Hoặc Link
document.addEventListener('DOMContentLoaded', function()
{
        const options_them = document.getElementById("options_them");
        
        options_them.addEventListener("change", function () {
            const selectedValue = options_them.value;
            
            switch (selectedValue) {
                case "link_them_select":
                {
                   var PT_show = document.querySelectorAll(".them_link_hide");
                   var PT_hide = document.querySelectorAll(".them_file_hide");
                   PT_show.forEach(PT_show => {
                   PT_show.style.display = "block";
            });
                   PT_hide.forEach(PT_hide => {
                   PT_hide.style.display = "none";
                   });
                   document.them.lof_them.value = "1";                 
                }
                    break;
                case "file_them_select":
                    {
                   var PT_show = document.querySelectorAll(".them_file_hide");
                   var PT_hide = document.querySelectorAll(".them_link_hide");
                   PT_show.forEach(PT_show => {
                   PT_show.style.display = "block";
            });
                   PT_hide.forEach(PT_hide => {
                   PT_hide.style.display = "none";
                   });
                   document.them.lof_them.value = "2";
                }
                    break;
            }
        });
});
                                            //Thêm Data từ bảng vào Form Thêm
function sua()
{
    var i = event.target.closest('tr').rowIndex;
    var ta = document.getElementById('song_table_id');
    var song_id = ta.rows[i].cells[0].innerText;
    var title = ta.rows[i].cells[1].innerText;
    var artist_id = ta.rows[i].cells[2].innerText;
    var href = ta.rows[i].cells[3].innerText;
    var image = ta.rows[i].cells[4].innerText;
    
    document.them.song_id.value = song_id;
    document.them.title.value = title;
    console.log(title);
    document.them.artist_id.value = artist_id;
    document.them.link_them.value = href;
    
    document.them.style.display = "block";
    var form_change_action = document.getElementById("them");
    form_change_action.action = "./sua";
    document.them.old_href.value = href;
    document.them.old_img.value = image;
    document.getElementById("form_title").innerText = "SỬA BÀI HÁT";

}

// Sua Form Thay doi text box va File
document.addEventListener('DOMContentLoaded', function()
{
    const fileInput = document.getElementById('file_them');
    const imgInput = document.getElementById('image');
    fileInput.addEventListener('change', () => {
            
    if (fileInput.files.length > 0) {
        document.them.file_change.value = "1";
        console.log(document.them.file_change.value);
            }
        });
     
    const textBox = document.getElementById('link_them');
    
    textBox.addEventListener('input', () => {
            document.them.link_change.value = "1";
            console.log(document.them.link_change.value);
        });
        
    imgInput.addEventListener('change', () => {
            
    if (imgInput.files.length > 0) {
        document.them.img_change.value = "1";
        console.log(document.them.img_change.value);
            }
        });
});

function test()
{
        alert(document.them.song_id.value + "; " + document.them.old_href.value + "; " + document.them.old_img.value);
        document.them.song_id.value;
        document.them.old_href.value;
        document.them.old_img.value;
}

function xoa()
{
 //   var form_change_action = document.getElementById("them");
 //   form_change_action.action = "./xoa";
    
    var i = event.target.closest('tr').rowIndex;
    var ta = document.getElementById('song_table_id');
    var song_id = ta.rows[i].cells[0].innerText;
    var href = ta.rows[i].cells[3].innerText;
    var image = ta.rows[i].cells[4].innerText;
    document.xoa_song.song_id.value = song_id;
    document.xoa_song.old_href.value = href;
    document.xoa_song.old_img.value = image;
    var xs = document.getElementById("xoa_song");
    xs.submit();
}


function tim_song()
{
    var tim_value = document.getElementById("search_song").value;
    
    var tim_op = document.getElementById("tim_op");
    if(tim_op.value === "tim_op_select")
    {
        alert("Chọn giá trị tìm kiếm");
    }
    else
    {
        document.tim_bh.tim_v.value = tim_value;
        document.tim_bh.tim_w.value = tim_op.value;
        document.getElementById("tim_bh").submit();
    }
    
    
}


function reload() 
{
        window.location.href = 'LoadData';  // Redirect to the servlet
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

