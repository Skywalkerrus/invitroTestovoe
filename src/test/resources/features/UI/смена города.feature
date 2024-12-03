#language:ru
@test2 @UI @All @browserMax
Функционал:

  Сценарий: меняем город
    #И открыть страницу "https://www.invitro.ru/moscow/radiology/"
    И ожидать секунд 1
    И выполнено нажатие на элемент с xpath: ".//div[@id='city']"
    И ожидать секунд 3
    И выполнено нажатие на элемент с xpath: ".//div[@class='city__change']/descendant::span[contains(text(), 'Выбрать')]"
    И ожидать секунд 3
    И в поле с xpath: ".//input[@class='change-city-search-input']" введено значение "Омск"
    И выполнено нажатие на элемент по тексту "Омск"
    И ожидать секунд 3
    И в поле с xpath: ".//div[@id='city']" значение текста равно "Омск"