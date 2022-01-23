$(function() {
    console.log('jQuery 読み込み完了');

    // csrf対策
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    // 入力ボタン押下時処理
    $('#InputButton').on('click', function() {
        alert('入力ボタン押下');
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
            money: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        }

        console.log(params);

        $.post(
            '/toppage-api/insert-consumption',
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