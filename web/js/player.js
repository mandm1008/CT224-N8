/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const playerManager = {
    // data
    data: null,
    songs: [],
    contextPath: "",
    currentSongIndex: 0,

    // player
    audioPlayer: null,

    // setting
    isPlaying: false,
    isShuffling: false,
    isRepeating: false,

    // elements
    songThumbnailElement: document.querySelector("img.song-thumbnail"),
    songTitleElement: document.querySelector(".song-title"),
    songArtistElement: document.querySelector(".song-artist"),
    playButton: document.querySelector('#playBtn'),

    setContextPath(path) {
        this.contextPath = path;
    },

    init() {
        this.audioPlayer = new Audio();
        this.addEventListeners();
        this.updateTimer();
    },

    getDataFromJson(json) {
        this.data = JSON.parse(json);
        this.setPlaylist(this.data);
    },

    setPlaylist(data) {
        this.data = data;
        this.songs = data.songs;
        this.setMusicData(this.songs[this.currentSongIndex]);
    },

    setMusicData(song) {
        if (this.audioPlayer) {
            this.audioPlayer.src = this.contextPath + song.href;
            this.audioPlayer.load();

            this.songThumbnailElement.src = this.contextPath + song.image;
            this.songTitleElement.innerText = song.title;
            this.songArtistElement.innerText = song.artistName;
        }
    },

    playMusic() {
        if (this.audioPlayer) {
            this.audioPlayer.play();
            this.isPlaying = true;
            this.playButton.querySelector('img').src = this.contextPath + '/images/icons/pause-solid.png';
            console.log('Playing music...');
        }
    },

    pauseMusic() {
        if (this.audioPlayer && this.isPlaying) {
            this.audioPlayer.pause();
            this.isPlaying = false;
            this.playButton.querySelector('img').src = this.contextPath + '/images/icons/play-solid.png';
            console.log('Pausing music...');
        }
    },

    restartMusic() {
        if (this.audioPlayer) {
            this.audioPlayer.currentTime = 0;
            this.playMusic();
            console.log('Restarting music...');
        }
    },

    nextMusic() {
        if (this.isShuffling) {
            this.currentSongIndex = Math.floor(Math.random() * this.songs.length);
        } else {
            this.currentSongIndex = (this.currentSongIndex + 1) % this.songs.length;
        }
        this.setMusicData(this.songs[this.currentSongIndex]);
        this.playMusic();
        console.log('Playing next song...');
    },

    prevMusic() {
        this.currentSongIndex = (this.currentSongIndex - 1 + this.songs.length) % this.songs.length;
        this.setMusicData(this.songs[this.currentSongIndex]);
        this.playMusic();
        console.log('Playing previous song...');
    },

    addEventListeners() {
        if (this.audioPlayer) {
            this.audioPlayer.addEventListener('ended', (() => {
                if (this.isRepeating) {
                    this.restartMusic();
                } else {
                    this.nextMusic();
                }
            }).bind(this));
        }

        document.getElementById('playBtn').addEventListener('click', () => {
            if (this.isPlaying) {
                this.pauseMusic();
            } else {
                this.playMusic();
            }
        });

        document.getElementById('nextBtn').addEventListener('click', () => {
            this.nextMusic();
        });

        document.getElementById('prevBtn').addEventListener('click', () => {
            this.prevMusic();
        });

        document.getElementById('shuffleBtn').addEventListener('click', () => {
            this.isShuffling = !this.isShuffling;
            console.log('Shuffle mode:', this.isShuffling);
            if (this.isShuffling) {
                document.getElementById('shuffleBtn').style = "opacity: 1";
            } else {
                document.getElementById('shuffleBtn').style = "opacity: 0.4";
            }
        });

        document.getElementById('repeatBtn').addEventListener('click', () => {
            this.isRepeating = !this.isRepeating;
            console.log('Repeat mode:', this.isRepeating);
            if (this.isRepeating) {
                document.getElementById('repeatBtn').style = "opacity: 1";
            } else {
                document.getElementById('repeatBtn').style = "opacity: 0.4";
            }
        });

        document.getElementById('progressBar').addEventListener('input', (event) => {
            const seekTime = (event.target.value / 100) * this.audioPlayer.duration;
            this.audioPlayer.currentTime = seekTime;
        });
    },

    updateTimer() {
        setInterval(() => {
            if (this.audioPlayer) {
                const currentTime = this.audioPlayer.currentTime;
                const duration = this.audioPlayer.duration;

                document.getElementById('currentTime').textContent = this.formatTime(currentTime);
                document.getElementById('durationTime').textContent = this.formatTime(duration);

                const progress = (currentTime / duration) * 100;
                document.getElementById('progressBar').value = progress || 0;
            }
        }, 1000);
    },

    formatTime(seconds) {
        const minutes = Math.floor(seconds / 60);
        const secs = Math.floor(seconds % 60);
        return `${minutes < 10 ? '0' : ''}${minutes}:${secs < 10 ? '0' : ''}${secs}`;
    }
};

playerManager.init();

playerManager.setContextPath(metadata.contextPath);
playerManager.getDataFromJson(metadata.jsonSongs);
