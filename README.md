# final
1. Склонируй репозиторий из гита
https://github.com/uladzimirAAsanko/final
2. Убедиьс что ты установил 9 томкат( это важно если установил не 9 то установи 9)
3. Открой intelij idea и открой проект который склонировал 
4. там нажми нью конфигурейшен(плюсик слева) и выбери TomCat Server Local
5. после того как добавил конфигурацию тебе нужно будет добавить новый артифакт, там даже кнопка будет фикс
6.теперь смотри у тебя должно быть настроено 2 папки конфига они лежат по пути final/src/main/resources/config в датабейз тебе надо просто будет добавить jdbc к базе данных и поменять пароль и логин к бд
остальное можешь не трогать в location ты должен будешь обновить местоположения скрипта(он есть в папке final/src), и папку куда будут сохраняться изображения
7. В базе данных выполни следущию команду
create table plot (
                      plot_id SERIAL PRIMARY KEY  NOT NULL,
                      plot_location varchar(40) NOT NULL ,
                      plot_name varchar(40) NOT NULL unique,
                      param DOUBLE PRECISION NOT NULL
);
8. потом ебрешь отсюда файл https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.20/postgresql-42.2.20.jar просто переди по ссылки он скачается и закинь его в папка_томката\lib
9. потом нажимаешь в идее ран(зеленый треугольник) и у тебя будет страничка на котором ты будешь вбивать параметры и график будет рисосваться и сохраняться, там только нужэно будет указать имя(будет создаваться директория с этим именем)
10. график может загружаться чуть позже чем страница из-за того что питон будет отрабатывать медленее ем джава
