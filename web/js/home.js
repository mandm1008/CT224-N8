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