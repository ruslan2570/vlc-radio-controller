# vlc-radio-controller
### Обзор
vlc-radio-controller - это небольшое Spring-приложение для управление плеером VLC.

#### Возможности
1. Управление плеером: переключение на следующий или предыдущий трек
2. Перезапуск плеера
3. получение информации о текущем треке

#### Технологии
- Java 17
- Spring Framework 2.7.5

### Пример скрипта для запуска Vlc

```
export LANG="en_US.UTF-8"
vlc /home/ruslan/Resources/radio/ --sout="#transcode{vcodec=none,acodec=mp3,ab=320,channels=2,samplerate44100}:http{mux=mp3,dst=:8042/music}" --sout-keep --loop -v --random
```
