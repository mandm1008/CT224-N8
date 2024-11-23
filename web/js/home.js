/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const sliderTop = {
    sliderElements: [],
    total: 0,
    index: 0,

    init(sliderWrapper) {
        this.sliderElements = sliderWrapper.querySelectorAll('.slider-element');
        this.total = this.sliderElements.length;
        this.index = 0;

        document.querySelector('.next-btn').addEventListener('click', () => {
            this.index = (this.index + 1) % this.total;
            this.updateSlider();
        });

        document.querySelector('.prev-btn').addEventListener('click', () => {
            this.index = (this.index - 1 + this.total) % this.total;
            this.updateSlider();
        });
    },

    updateSlider() {
        const index = this.index;
        this.sliderElements.forEach((element, i) => {
            element.classList.remove('prev', 'active', 'next', 'new-next');
            if (i === index) {
                element.classList.add('active');
            } else if (i === (index + 1) % this.total) {
                element.classList.add('prev');
            } else if (i === (index + 2) % this.total) {
                element.classList.add('next');
            } else if (i === (index + 3) % this.total) {
                element.classList.add('new-next');
            }
        });
    }
};

sliderTop.init(document.querySelector(".home-slider"));
sliderTop.updateSlider();

window.setInterval(() => {
    sliderTop.index = (sliderTop.index + 1) % sliderTop.total;
    sliderTop.updateSlider();
}, 5000);

const musicElements = document.querySelectorAll(".music-element");

musicElements.forEach((musicElement) => {
    const menuButton = musicElement.querySelector(".music-menu--icon");
    const menuMusic = musicElement.querySelector(".music-menu");
    
    if (menuButton === null) {
        return;
    }
    
    if (menuMusic === null) {
        return;
    }

    menuButton.onclick = (e) => {
        menuMusic.classList.toggle("active");

        const rect = musicElement.getBoundingClientRect();
        const menuRect = menuMusic.getBoundingClientRect();

        if (rect.right + menuRect.width > window.innerWidth) {
            menuMusic.style.left = 'auto';
            menuMusic.style.right = '50%'; // Hiển thị ở bên trái
        } else {
            menuMusic.style.left = '100%';
            menuMusic.style.right = 'auto';
        }

        e.stopPropagation();
    };

    menuMusic.onclick = (e) => {
        e.stopPropagation();
    };

    window.addEventListener("click", (e) => {
        menuMusic.classList.remove("active");
    });
});
