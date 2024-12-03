#language:ru
@test4 @UI @All
Функционал: корзина анализов

  Сценарий: прокликиваем все пункты меню в медуслугах
    И открыть страницу "https://www.invitro.ru/analizes/for-doctors/"
    И выполнено нажатие на элемент с xpath: ".//a[@data-product-name='Глюкоза (в крови) (Glucose)']"
    И взять текст поля с xpath ".//div[@data-item-id='3401']/descendant::div[@class='analyzes-item__total--sum']" очистить от мусора " ₽" и сохранить в переменную с именем: "saveSum"
    И выполнено нажатие на элемент с xpath: ".//div[@id='headerCartDynamic']"
    И взять текст поля с xpath ".//div[@class='SummaryBlock_costTotal__n7asM SummaryBlock_cartCostPosition__7GXLO']/div/span" очистить от мусора "RUB , ₽" и сохранить в переменную с именем: "sumInKorzina"
    И взять значение из сохраненной переменной: "sumInKorzina" и сравнить в цифре с другой сохраненной переменной: "saveSum"
    И взять значение из сохраненной переменной: "sumInKorzina" и сравнить в цифре с значением: 10000
    И взять значение из сохраненной переменной: "saveSum" и сравнить в цифре с другой сохраненной переменной: "sumInKorzina"
    И взять значение из сохраненной переменной: "sumInKorzina" и сравнить в цифре с другой сохраненной переменной: "sumInKorzina"