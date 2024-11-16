/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

class YoutubeManager {
    constructor(iframeId = 'youtube-iframe') {
        this.YOUTUBE_KEY = "https://www.youtube.com/watch";
        this.player = null;
        this.iframeId = iframeId;
        this.videoId = null;
        this.onReady = [];
        this.onStateChange = [];
        this.videoData = null;

        this.init();
    }

    checkYoutube(href) {
        return href.includes(this.YOUTUBE_KEY);
    }

    detachId(href) {
        const urlParams = new URLSearchParams(new URL(href).search);
        const videoId = urlParams.get("v");

        return videoId;
    }

    async fetchYouTubeData(videoId) {
        const url = `https://www.googleapis.com/youtube/v3/videos?id=${videoId}&key=AIzaSyBPjNeE5gLTC6hyFy9DWH7BxHohVHQhZi4&part=snippet`;

        const response = await fetch(url);
        if (!response.ok)
            throw new Error("Network response was not ok");

        const data = await response.json();

        if (data.items && data.items.length > 0) {
            const videoData = data.items[0];
            console.log('Title:', videoData.snippet.title);
            console.log('Description:', videoData.snippet.description);
            console.log('Channel Name:', videoData.snippet.channelTitle);
            return videoData.snippet;
        } else {
            console.log("Video not found");
            return null;
        }
    }

    init() {
        window.onYouTubeIframeAPIReady = () => this.createPlayer();
    }

    createPlayer() {
        this.player = new YT.Player(this.iframeId, {
            videoId: this.videoId,
            events: {
                'onReady': (event) => this.onPlayerReady(event),
                'onStateChange': (event) => this.onPlayerStateChange(event),
            }
        });
        console.log(this.player);
    }

    onPlayerReady(event) {
        this.onReady.forEach(callback => callback(event));
    }

    onPlayerStateChange(event) {
        this.onStateChange.forEach(callback => callback(event));
    }

    addEventReady(callback) {
        this.onReady.push(callback);
    }

    addEventStateChange(callback) {
        this.onStateChange.push(callback);
    }

    loadVideo(videoId, onLoadCallback) {
        this.videoId = videoId;
        if (!this.player) {
            this.createPlayer();
        }

        this.fetchYouTubeData(videoId).then((data) => {
            this.videoData = data;
            console.log(data);
        });
        this.addEventReady(onLoadCallback);
        this.addEventStateChange(onLoadCallback);
        if (this.player.loadVideoById)
            this.player.loadVideoById(videoId);
    }

    clearEvents() {
        this.onStateChange = [];
        this.onReady = [];
    }

    play() {
        this.player.playVideo();
    }
    pause() {
        this.player.pauseVideo();
    }
    stop() {
        this.player.stopVideo();
    }
    seekTo(seconds, allowSeekAhead = true) {
        this.player.seekTo(seconds, allowSeekAhead);
    }
    setVolume(volumeLevel) {
        this.player.setVolume(volumeLevel);
    }
    mute() {
        this.player.mute();
    }
    unmute() {
        this.player.unMute();
    }
    isMuted() {
        return this.player.isMuted();
    }
    setVolume(volume) {
        if (this.player && this.player.setVolume) {
            this.player.setVolume(volume);
        }
    }
    getCurrentVolume() {
        if (this.player && this.player.getVolume) {
            return this.player.getVolume();
        } else {
            return 1;
        }
    }

    getCurrentTime() {
        if (this.player.getCurrentTime)
            return this.player.getCurrentTime();
    }
    getDuration() {
        if (this.player.getDuration)
            return this.player.getDuration();
    }
    getVideoData() {
        if (this.player.getVideoData)
            return this.player.getVideoData();
    }
    getPlayerState() {
        if (this.player.getPlayerState)
            return this.player.getPlayerState();
    }
    getThumbnail() {
        return `https://img.youtube.com/vi/${this.videoId}/hqdefault.jpg`;
    }
}

class PlayerManager {
    constructor(
            title = ".song-title",
            thumbnail = "img.song-thumbnail",
            artist = ".song-artist",
            playButton = "#playBtn",
            nextButton = "#nextBtn",
            prevButton = "#prevBtn",
            shuffleButton = "#shuffleBtn",
            repeatButton = "#repeatBtn",
            progressBar = "#progressBar",
            currentTime = "#currentTime",
            durationTime = "#durationTime",
            volume = "#volumeButton",
            volumeInput = "#volumeInput"
            ) {
        this.data = null;
        this.songs = [];
        this.contextPath = "";
        this.currentSongIndex = 0;

        this.audioPlayer = new Audio();
        this.isPlaying = false;
        this.isShuffling = false;
        this.isRepeating = false;

        this.songThumbnailElement = document.querySelector(thumbnail);
        this.songTitleElement = document.querySelector(title);
        this.songArtistElement = document.querySelector(artist);
        this.playButton = document.querySelector(playButton);
        this.nextButtonElement = document.querySelector(nextButton);
        this.prevButtonElement = document.querySelector(prevButton);
        this.shuffleButtonElement = document.querySelector(shuffleButton);
        this.repeatButtonElement = document.querySelector(repeatButton);
        this.progressBarElement = document.querySelector(progressBar);
        this.currentTimeElement = document.querySelector(currentTime);
        this.durationTimeElement = document.querySelector(durationTime);
        this.volumeButtonElement = document.querySelector(volume);
        this.volumeInputElement = document.querySelector(volumeInput);

        this.init();
    }

    setContextPath(path) {
        this.contextPath = path;
    }

    init() {
        this.youtubeManager = new YoutubeManager();
        this.audioPlayer = new Audio();
        this.addEventListeners();
        this.updateTimer();
    }

    getDataFromJson(json) {
        this.data = JSON.parse(json);
        this.setPlaylist(this.data);
    }

    setPlaylist(data) {
        this.data = data;
        this.songs = data.songs;
        this.setMusicData(this.songs[this.currentSongIndex]);
    }

    setMusicData(song) {
        console.log(song);
        if (this.youtubeManager.checkYoutube(song.href)) {
            if (this.audioPlayer)
                this.audioPlayer.pause();

            const videoId = this.youtubeManager.detachId(song.href);

            this.youtubeManager.loadVideo(videoId, () => {
                const YTData = this.youtubeManager.getVideoData();
                console.log(YTData);
                this.songThumbnailElement.src = this.youtubeManager.getThumbnail();
                this.songTitleElement.innerText = this.youtubeManager.videoData?.title || YTData.title;
                this.songArtistElement.innerText = this.youtubeManager.videoData?.channelTitle || YTData.author;
            });
        } else {
            if (this.youtubeManager.player)
                this.youtubeManager.stop();
            this.youtubeManager.clearEvents();

            this.audioPlayer.src = song.href;
            this.audioPlayer.load();

            this.songThumbnailElement.src = song.image;
            this.songTitleElement.innerText = song.title;
            this.songArtistElement.innerText = song.artistName;
        }

        if (this.isPlaying) {
            this.playMusic();
        }

        fetch(this.contextPath + "/api/user/action?type=music&id=" + song.songId);
    }

    playMusic() {
        if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
            this.youtubeManager.play();
        } else {
            this.audioPlayer.play();
        }
        this.isPlaying = true;
        this.playButton.querySelector('img').src = this.contextPath + '/images/icons/pause-solid.png';
        console.log('Playing music...');
    }

    pauseMusic() {
        if (this.isPlaying) {
            if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                this.youtubeManager.pause();
            } else {
                this.audioPlayer.pause();
            }
            this.isPlaying = false;
            this.playButton.querySelector('img').src = this.contextPath + '/images/icons/play-solid.png';
            console.log('Pausing music...');
        }
    }

    restartMusic() {
        if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
            this.youtubeManager.seekTo(0);
            this.youtubeManager.play();
        } else {
            this.audioPlayer.currentTime = 0;
            this.playMusic();
        }

        console.log('Restarting music...');
    }

    nextMusic() {
        if (this.isShuffling) {
            this.currentSongIndex = Math.floor(Math.random() * this.songs.length);
        } else {
            this.currentSongIndex = (this.currentSongIndex + 1) % this.songs.length;
        }
        this.setMusicData(this.songs[this.currentSongIndex]);
        this.playMusic();
        console.log('Playing next song...');
    }

    prevMusic() {
        this.currentSongIndex = (this.currentSongIndex - 1 + this.songs.length) % this.songs.length;
        this.setMusicData(this.songs[this.currentSongIndex]);
        this.playMusic();
        console.log('Playing previous song...');
    }

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

        this.youtubeManager.addEventStateChange((event) => {
            if (event.data === YT.PlayerState.ENDED) {
                if (playerManager.isRepeating) {
                    this.restartMusic();
                } else {
                    this.nextMusic();
                }
            }
        });

        this.playButton.addEventListener('click', () => {
            if (this.isPlaying) {
                this.pauseMusic();
            } else {
                this.playMusic();
            }
        });

        this.nextButtonElement.addEventListener('click', () => {
            this.nextMusic();
        });

        this.prevButtonElement.addEventListener('click', () => {
            this.prevMusic();
        });

        this.shuffleButtonElement.addEventListener('click', () => {
            this.isShuffling = !this.isShuffling;
            console.log('Shuffle mode:', this.isShuffling);
            this.shuffleButtonElement.style.opacity = this.isShuffling ? "1" : "0.4";
        });

        this.repeatButtonElement.addEventListener('click', () => {
            this.isRepeating = !this.isRepeating;
            console.log('Repeat mode:', this.isRepeating);
            this.repeatButtonElement.style.opacity = this.isRepeating ? "1" : "0.4";
        });

        this.progressBarElement.addEventListener('input', (event) => {
            if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                const seekTime = (event.target.value / 100) * this.youtubeManager.getDuration();
                this.youtubeManager.seekTo(seekTime);
            } else {
                const seekTime = (event.target.value / 100) * this.audioPlayer.duration;
                this.audioPlayer.currentTime = seekTime;
            }
        });

        this.volumeButtonElement?.addEventListener("click", (event) => {
            const imgVolume = this.volumeButtonElement.querySelector("img");

            if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                if (this.youtubeManager.getCurrentVolume() === 0) {
                    this.youtubeManager.setVolume(100);
                    imgVolume.src = this.contextPath + "/images/icons/volume-high-solid.png";
                    this.volumeInputElement.value = 100;
                } else {
                    this.youtubeManager.setVolume(0);
                    imgVolume.src = this.contextPath + "/images/icons/volume-xmark-solid.png";
                    this.volumeInputElement.value = 0;
                }
            } else {
                if (this.audioPlayer.volume === 0) {
                    this.audioPlayer.volume = 1;
                    imgVolume.src = this.contextPath + "/images/icons/volume-high-solid.png";
                    this.volumeInputElement.value = 100;
                } else {
                    this.audioPlayer.volume = 0;
                    imgVolume.src = this.contextPath + "/images/icons/volume-xmark-solid.png";
                    this.volumeInputElement.value = 0;
                }
            }
        });

        this.volumeInputElement?.addEventListener("input", (event) => {
            const imgVolume = this.volumeButtonElement.querySelector("img");
            const value = this.volumeInputElement.value / 100;

            if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                this.youtubeManager.setVolume(value * 100);
            } else {
                this.audioPlayer.volume = value;
            }

            if (value === 0) {
                imgVolume.src = this.contextPath + "/images/icons/volume-xmark-solid.png";
            } else {
                imgVolume.src = this.contextPath + "/images/icons/volume-high-solid.png";
            }
        });
    }

    updateTimer() {
        setInterval(() => {
            if (this.youtubeManager.checkYoutube(this.songs[this.currentSongIndex].href)) {
                if (this.youtubeManager.player) {
                    const currentTime = this.youtubeManager.getCurrentTime();
                    const duration = this.youtubeManager.getDuration();

                    this.currentTimeElement.textContent = this.formatTime(currentTime);
                    this.durationTimeElement.textContent = this.formatTime(duration);

                    const progress = (currentTime / duration) * 100;
                    this.progressBarElement.value = progress || 0;
                }
            } else {
                if (this.audioPlayer) {
                    const currentTime = this.audioPlayer.currentTime;
                    const duration = this.audioPlayer.duration;

                    this.currentTimeElement.textContent = this.formatTime(currentTime);
                    this.durationTimeElement.textContent = this.formatTime(duration);

                    const progress = (currentTime / duration) * 100;
                    this.progressBarElement.value = progress || 0;
                }
            }
        }, 1000);
    }

    formatTime(seconds) {
        const minutes = Math.floor(seconds / 60);
        const secs = Math.floor(seconds % 60);
        return `${minutes < 10 ? '0' : ''}${minutes}:${secs < 10 ? '0' : ''}${secs}`;
    }

    isInList(song) {
        return this.songs.findIndex((item) => item.songId === song.songId);
    }

    playNowSong(jsonSong) {
        const newSong = JSON.parse(jsonSong);
        const index = this.isInList(newSong);
        if (index !== -1) {
            this.currentSongIndex = index;
            this.setMusicData(this.songs[index]);
        } else {
            this.songs.push(newSong);
            this.currentSongIndex = this.songs.length - 1;
            this.setMusicData(newSong);
        }

        this.playMusic();
    }

    addToPlaylist(jsonSong) {
        const newSong = JSON.parse(jsonSong);
        const index = this.isInList(newSong);
        if (index === -1) {
            this.songs.push(newSong);
        }
    }

    downloadSong(href) {
        this.download(href);
    }

    download(dataUrl) {
        const link = document.createElement("a");
        link.target = "_blank";

        link.href = dataUrl;
        document.body.appendChild(link);
        link.click();

        document.body.removeChild(link);
    }

    shareSong(songId) {
        const originUrl = window.location.origin + this.contextPath;

        this.copyToClipboard(originUrl + "/songs.jsp?id=" + songId);
    }

    copyToClipboard(text) {
        navigator.clipboard.writeText(text).then(function () {
            console.log("Sao chép thành công!");
        }).catch(function (err) {
            console.error("Lỗi khi sao chép: ", err);
        });
    }
}
