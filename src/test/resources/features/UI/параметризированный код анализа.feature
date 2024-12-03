#language:ru
@test5 @UI @All @browserMax
Функционал:

  Сценарий: вводим код анализа в поиск
    #И открыть страницу "https://www.invitro.ru/radiology/"
    И ввести код анализа: "1515" в поле поиска
    И ожидать секунд 5