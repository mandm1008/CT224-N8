/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const accountVerify = {
    mgsElement: document.querySelector(".error-message"),

    // Cấu hình các thông số
    settings: {
        emailPattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, // Pattern cho email
        minUsernameLength: 3, // Độ dài tối thiểu cho username
        minPasswordLength: 6, // Độ dài tối thiểu cho password
    },

    // Hàm kiểm tra trường có trống không
    verifyEmpty(input, msg) {
        if (input === null || input.trim().length === 0) {
            this.mgsElement.innerText = msg;
            return false;
        }
        this.mgsElement.innerText = ''; // Clear message nếu không có lỗi
        return true;
    },

    // Hàm kiểm tra độ dài tối thiểu của chuỗi
    verifyMinLength(input, minLength, msg) {
        if (input.trim().length < minLength) {
            this.mgsElement.innerText = msg;
            return false;
        }
        this.mgsElement.innerText = ''; // Clear message nếu không có lỗi
        return true;
    },

    // Hàm kiểm tra định dạng email
    verifyEmailFormat(input, msg) {
        if (!this.settings.emailPattern.test(input.trim())) {
            this.mgsElement.innerText = msg;
            return false;
        }
        this.mgsElement.innerText = ''; // Clear message nếu không có lỗi
        return true;
    },

    // Hàm xác thực form đăng nhập
    verifyLoginForm(username, password) {
        if (!this.verifyEmpty(username, "Username is required")) return false;
        if (!this.verifyEmpty(password, "Password is required")) return false;
        return true;
    },

    // Hàm xác thực form đăng ký
    verifySignupForm(username, email, password, confirmPassword) {
        if (!this.verifyEmpty(username, "Username is required")) return false;
        if (!this.verifyMinLength(username, this.settings.minUsernameLength, `Username must be at least ${this.settings.minUsernameLength} characters long`)) return false;
        if (!this.verifyEmpty(email, "Email is required")) return false;
        if (!this.verifyEmailFormat(email, "Invalid email format")) return false;
        if (!this.verifyEmpty(password, "Password is required")) return false;
        if (!this.verifyMinLength(password, this.settings.minPasswordLength, `Password must be at least ${this.settings.minPasswordLength} characters long`)) return false;
        if (password !== confirmPassword) {
            this.mgsElement.innerText = "Passwords do not match";
            return false;
        }
        this.mgsElement.innerText = ''; // Clear message nếu không có lỗi
        return true;
    }
};

// Hàm xác thực form login
function handleLoginSubmit(event) {
    const username = document.querySelector("#username").value;
    const password = document.querySelector("#password").value;
    if (!accountVerify.verifyLoginForm(username, password)) {
        event.preventDefault(); // Ngăn gửi form nếu không hợp lệ
    }
}

// Hàm xác thực form signup
function handleSignupSubmit(event) {
    const username = document.querySelector("#username").value;
    const email = document.querySelector("#email").value;
    const password = document.querySelector("#password").value;
    const confirmPassword = document.querySelector("#confirmPassword").value;
    if (!accountVerify.verifySignupForm(username, email, password, confirmPassword)) {
        event.preventDefault();
    }
}
