# Задание 3
## Постановка задачи

**Создать шаблон проекта.**

На разметке определить круглый ProgressBar (изначально c Visibility.GONE), TextView (без текста) и Button.

**При нажатии на кнопку:**

- кнопка становится задизейсбленной (setEnabled(false)) 
- ProgressBar становится видимым ( Visibility.VISIBLE ) 
- в TextView появляется текст - “Loading..” 
- запускается задача. Подробности ниже.

**Задача** - имитация работы в фоновом потоке с помощью перевода этого потока в спящий режим на 5 секунд. ( TimeUnit.MILLISECONDS.sleep(5000); )

**После выполнения задачи:**
- кнопка становится активной (setEnabled(true))
- ProgressBar становится невидимым
- в TextView появляется текст “Ready”

**Проблема.**
Состояние экрана во время загрузки должно переживать переворот экрана ( пересоздание активити). Уже запущенный поток не должен запускаться заново.

Рассмотреть несколько способов решения: 

- использовать retain fragment + Executor/HandlerThread/AsyncTask (легко) 
- использовать AsynсTaskLoader (средне) 
- запускать поток из Application/Singleton, хранить состояние там же. (средне) 
- запуск service с одновременной привязкой ( startService / bindService) (средне)

Выбрать один из среднего уровня сложности, дать осмысленное имя, заархивировать, отправить на проверку.

## Скриншоты решения


## Примечание
В решении задания использовался **способ с использованием AsyncTaskLoader**