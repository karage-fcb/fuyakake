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
        // 入力モーダル表示
        $('#inputModal').modal('show');
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
            getConsumption();
        }).fail(function() {
            console.log('Post失敗!!');
        }).always(function() {
            console.log('Post終了');
            $('#inputModal').modal('hide');
        });
    });
});

function getConsumption() {
    $.get(
        '/toppage-api/get-consumption'
    ).done(function(data) {
        console.log('消費情報取得成功!');
        console.log(data);
        $('#ConsumptionTable').find('tbody tr').remove();
        data.consumptionList.forEach(function(element) {
            console.log(element);
            html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>'
            $('#ConsumptionTable').append(html);
        });
    }).fail(function() {
        console.log('消費情報取得失敗!');
    }).always(function() {
        console.log('消費情報取得処理終了');
    })
}