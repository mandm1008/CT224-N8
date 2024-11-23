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