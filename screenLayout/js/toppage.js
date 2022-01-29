$(function() {
    console.log('jQuery 読み込み完了');

    // 入力ボタン押下時処理
    $('#InputButton').on('click', function() {
        // alert('入力ボタン押下');
    });
    
    // 保存ボタン押下時処理
    $('#SaveButton').on('click', function() {
        const date = $('#DateInput').val();
        const money = $('#MoneyInput').val();
        const accountId = $('#AccountInput').val();
        const bigCategoryId = $('#BigCategoryInput').val();
        const middleCategoryId = $('#MiddleCategoryInput').val();
        const memo = $('#memotextarea').val();

        const params = {
            consumptionMoney: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        }

        $.post(
            'http://127.0.0.1:8080/toppageapi/insertconsumption',
            params
        ).done(function(data) {
            console.log(data);
        }).fail(function() {
            console.log('Post失敗!!');
        }).always(function() {
            console.log('Post終了');
        });
    });
});