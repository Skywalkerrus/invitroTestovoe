#language:ru
@test1 @UI @All @browserMax
Функционал: первое задание

  Сценарий: прокликиваем все пункты меню в медуслугах
    #И открыть страницу "https://www.invitro.ru/moscow/radiology/"
    И прокилик всех элементов панели мед услуги "//ul[@class='side-bar-second__list']/li"