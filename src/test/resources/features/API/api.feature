#language:ru
@test7 @API @All
Функционал:
  Сценарий: посылаем запрос Москве
    И выполнить get запрос на url: "https://www.invitro.ru/local/ajax/current-city.php" и сохранить ответ в переменную "Msk"
      | "INVITRO_RU_REGION=217;INVITRO_REGION_CODE=moscow;INVITRO_CITY_LK_GUID=f1c3c4f0-3426-4cda-8449-e5d326e02f97;INVITRO_CITY=%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0;" |
    И сравнить значения из переменной: "Msk" со значениями из таблицы по jsonPath
      | city  |   Москва  |
      | code  |   moscow  |
      | guid  | f1c3c4f0-3426-4cda-8449-e5d326e02f97 |
  Сценарий: посылаем запрос Лондону
    И выполнить get запрос на url: "https://www.invitro.ru/local/ajax/current-city.php" и сохранить ответ в переменную "Lnd"
      | "INVITRO_RU_REGION=218;INVITRO_REGION_CODE=london;INVITRO_CITY_LK_GUID=f1c3c4f0-yyyy-yyyy-yyyy-e5d326e02f97;INVITRO_CITY=London;" |
    И сравнить значения из переменной: "Lnd" со значениями из таблицы по jsonPath
      | city  |   London  |
      | code  |   london  |
      | guid  | f1c3c4f0-yyyy-yyyy-yyyy-e5d326e02f97 |
  Сценарий: посылаем запрос Баймаку
    И выполнить get запрос на url: "https://www.invitro.ru/local/ajax/current-city.php" и сохранить ответ в переменную "Baj"
      | "INVITRO_RU_REGION=219;INVITRO_REGION_CODE=bajmak;INVITRO_CITY_LK_GUID=f1c3c4f0-xxxx-xxxx-xxxx-e5d326e02f97;INVITRO_CITY=Bajmak;" |
    И сравнить значения из переменной: "Baj" со значениями из таблицы по jsonPath
      | city  |   Bajmak  |
      | code  |   bajmak  |
      | guid  | f1c3c4f0-xxxx-xxxx-xxxx-e5d326e02f97 |
