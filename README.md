<img src=https://user-images.githubusercontent.com/77673976/133929391-62fbb07b-0c1a-4246-9f3e-f7d29d13a930.png />

# О приложении
TxtMerger - небольшое консольное приложение, позволяющее сортировать и объединить предварительно отсортированные текстовые файлы в один новый файл.

# Технологии
- Код приложения написан на Java
- Использованные библиотеки:
    - [Picocli](https://github.com/remkop/picocli/) - библиотека, позволяющая создавать консольные Java приложения.
    - [Maven](https://github.com/apache/maven) - фреймворк для автоматизации сборки проектов.
    - [junit5](https://github.com/junit-team/junit5) - библиотека для модульного тестирования.

# Параметры
### Обязательные:

**-i** или **-s** - тип входных данных

**-out "output файл"**  - параметр, после которого необходимо указать output файл

**"input files"** - файлы с входными данными. Необходимо указать не менее одного

### Необязательные:

**-a** - сортировка по возрастанию (работает по-умолчанию)

**-d** - сортировка по убыванию

# Инструкция по запуску
1. Создайте исполняемый jar файл с помощью команды **mvn package**
2. Запустите jar файл с помощью команды java --jar target/cft_test_task-1.0-SNAPSHOT-jar-with-dependencies.jar -тип данных -вид сортировки -out "output файл" "input файлы"

## Интерфейс взаимодействия

```
TxtMerger is a small command line application that allow sort and merge txt files
Usage: TxtMerger -out "output-file" [options ...] "input-files..."
  -out    Output file
  -a      Ascending order sort
  -d      Descending order sort
  -h      Help message
  -i      Integer data type option
  -s      String data type option
  -V      Print version information
Created by Stanislav Shcheglov for CFT Shift
```
## Примеры запуска
```
java --jar cft_test_task-1.0-SNAPSHOT-jar-with-dependencies.jar -i -d -out out.txt in1.txt in2.txt in3.txt (для целых чисел по убыванию)
java --jar cft_test_task-1.0-SNAPSHOT-jar-with-dependencies.jar -s -d -out out.txt in1.txt in2.txt (для целых чисел по убыванию)
java --jar cft_test_task-1.0-SNAPSHOT-jar-with-dependencies.jar -i -out out.txt in1.txt (для целых чисел по возрастанию)
java --jar cft_test_task-1.0-SNAPSHOT-jar-with-dependencies.jar -s -out out.txt in1.txt (для строк чисел по возрастанию)
```
