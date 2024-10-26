/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

const YTManager = {
    YOUTUBE_KEY: "https://www.youtube.com/watch",
    player: null,
    iframeId: 'youtube-iframe',
    videoId: null,
    onReadyCallback: null,
    onStateChangeCallback: null,

    checkYoutube(href) {
        return href.includes(this.YOUTUBE_KEY);
    },

    detachId(href) {
        const urlParams = new URLSearchParams(new URL(href).search);
        const videoId = urlParams.get("v");

        return videoId;
    },

    init(onReadyCallback = null, onStateChangeCallback = null) {
        this.onReadyCallback = onReadyCallback;
        this.onStateChangeCallback = onStateChangeCallback;
        window.onYouTubeIframeAPIReady = () => this.createPlayer();
    },

    createPlayer() {
        this.player = new YT.Player(this.iframeId, {
            videoId: this.videoId,
            events: {
                'onReady': (event) => this.onPlayerReady(event),
                'onStateChange': (event) => this.onPlayerStateChange(event),
            }
        });
        console.log(this.player);
    },

    onPlayerReady(event) {
        if (this.onReadyCallback)
            this.onReadyCallback(event);
    },

    onPlayerStateChange(event) {
        if (this.onStateChangeCallback)
            this.onStateChangeCallback(event);
    },

    loadVideo(videoId) {
        this.videoId = videoId;
        if (this.player)
            this.player.loadVideoById(videoId);
    },

    play() {
        this.player.playVideo();
    },
    pause() {
        this.player.pauseVideo();
    },
    stop() {
        this.player.stopVideo();
    },
    seekTo(seconds, allowSeekAhead = true) {
        this.player.seekTo(seconds, allowSeekAhead);
    },
    setVolume(volumeLevel) {
        this.player.setVolume(volumeLevel);
    },
    mute() {
        this.player.mute();
    },
    unmute() {
        this.player.unMute();
    },
    isMuted() {
        return this.player.isMuted();
    },

    getCurrentTime() {
        return this.player.getCurrentTime();
    },
    getDuration() {
        return this.player.getDuration();
    },
    getVideoData() {
        return this.player.getVideoData();
    },
    getPlayerState() {
        return this.player.getPlayerState();
    }
};

YTManager.init();

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
        console.log(song);
        if (YTManager.checkYoutube(song.href)) {
            if (this.audioPlayer)
                this.audioPlayer.pause();

            const videoId = YTManager.detachId(song.href);
            YTManager.loadVideo(videoId);

            this.songThumbnailElement.src = this.contextPath + song.image;
            this.songTitleElement.innerText = song.title;
            this.songArtistElement.innerText = song.artistName;
        } else {
            if (YTManager.player)
                YTManager.stop();

            this.audioPlayer.src = this.contextPath + song.href;
            this.audioPlayer.load();

            this.songThumbnailElement.src = this.contextPath + song.image;
            this.songTitleElement.innerText = song.title;
            this.songArtistElement.innerText = song.artistName;
        }

        fetch(this.contextPath + "/api/user/action?type=music&id=" + song.songId);
    },

    playMusic() {
        if (YTManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
            YTManager.play();
        } else {
            this.audioPlayer.play();
        }
        this.isPlaying = true;
        this.playButton.querySelector('img').src = this.contextPath + '/images/icons/pause-solid.png';
        console.log('Playing music...');
    },

    pauseMusic() {
        if (this.isPlaying) {
            if (YTManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                YTManager.pause();
            } else {
                this.audioPlayer.pause();
            }
            this.isPlaying = false;
            this.playButton.querySelector('img').src = this.contextPath + '/images/icons/play-solid.png';
            console.log('Pausing music...');
        }
    },

    restartMusic() {
        if (YTManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
            YTManager.seekTo(0);
            YTManager.play();
        } else {
            this.audioPlayer.currentTime = 0;
            this.playMusic();
        }

        console.log('Restarting music...');
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

//////////////////////////////////////////////////
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

        if (YTManager.player) {
            YTManager.onStateChangeCallback = (event) => {
                if (event.data === YT.PlayerState.ENDED) {
                    if (playerManager.isRepeating) {
                        this.restartMusic();
                    } else {
                        this.nextMusic();
                    }
                }
            };
        }

        this.playButton.addEventListener('click', () => {
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
            document.getElementById('shuffleBtn').style.opacity = this.isShuffling ? "1" : "0.4";
        });

        document.getElementById('repeatBtn').addEventListener('click', () => {
            this.isRepeating = !this.isRepeating;
            console.log('Repeat mode:', this.isRepeating);
            document.getElementById('repeatBtn').style.opacity = this.isRepeating ? "1" : "0.4";
        });

        const progressBar = document.getElementById('progressBar');

        progressBar.addEventListener('input', (event) => {
            const seekTime = (event.target.value / 100) * this.audioPlayer.duration;
            if (YTManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                YTManager.seekTo(seekTime);
            } else {
                this.audioPlayer.currentTime = seekTime;
            }
        });
    },

    updateTimer() {
        setInterval(() => {
            if (YTManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                if (YTManager.player) {
                    const currentTime = YTManager.getCurrentTime();
                    const duration = YTManager.getDuration();

                    document.getElementById('currentTime').textContent = this.formatTime(currentTime);
                    document.getElementById('durationTime').textContent = this.formatTime(duration);

                    const progress = (currentTime / duration) * 100;
                    document.getElementById('progressBar').value = progress || 0;
                }
            } else {
                if (this.audioPlayer) {
                    const currentTime = this.audioPlayer.currentTime;
                    const duration = this.audioPlayer.duration;

                    document.getElementById('currentTime').textContent = this.formatTime(currentTime);
                    document.getElementById('durationTime').textContent = this.formatTime(duration);

                    const progress = (currentTime / duration) * 100;
                    document.getElementById('progressBar').value = progress || 0;
                }
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
