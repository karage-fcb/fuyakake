$(function () {
    console.log('jQuery 読み込み完了');

    // csrf対策
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    // 入力ボタン押下時処理
    $('#InputButton').on('click', function () {

        // 口座・カテゴリ情報の取得
        $.get(
            '/toppage-api/show-consumption-modal'
        ).done(function(data) {
            // セレクトボックスの中身を全件削除
            $('#ConsumptionAccountInput > option').remove();
            // 口座情報をセレクトボックスに追加する
            data.accountList.forEach((elm) => {
                $('#ConsumptionAccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
            });
        });

        // モーダル表示
        $('#inputModal').modal('show');

    });

    // モーダルの消費タグ押下時処理
    $('#ConsumptionLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            '/toppage-api/show-consumption-modal'
        ).done(function(data) {
            // セレクトボックスの中身を全件削除
            $('#ConsumptionAccountInput > option').remove();
            // 口座情報をセレクトボックスに追加する
            data.accountList.forEach((elm) => {
                $('#ConsumptionAccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
            });
        });
    });

    // モーダルの収入タグ押下時処理
    $('#IncomLink').on('click', function() {

        $.get(
            '/toppage-api/show-incom-modal'
        ).done(function(data) {
            // セレクトボックスの中身を全件削除
            $('#IncomAccountInput > option').remove();
            // 口座情報をセレクトボックスに追加する
            data.accountList.forEach((elm) => {
                $('#IncomAccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
            });
        });
    });

    // モーダルの投資タグ押下時処理
    $('#InvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            '/toppage-api/show-investment-modal'
        ).done(function(data) {
            // セレクトボックスの中身を全件削除
            $('#InvestmentAccountInput > option').remove();

            // 口座情報をセレクトボックスに追加する
            data.accountList.forEach((elm) => {
                $('#InvestmentAccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
            });
        });
    });

    // モーダルの自己投資タグ押下時処理
    $('#SelfInvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            '/toppage-api/show-self-investment-modal'
        ).done(function(data) {
            // セレクトボックスの中身を全件削除
            $('#SelfInvestmentAccountInput > option').remove();

            // 口座情報をセレクトボックスに追加する
            data.accountList.forEach((elm) => {
                $('#SelfInvestmentAccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
            });
        });
    });

    // 消費情報保存ボタン押下時処理
    $('#ConsumptionSaveButton').on('click', function () {
        // 入力値取得
        const date = $('#ConsumptionDateInput').val();
        const money = $('#ConsumptionMoneyInput').val();
        const accountId = $('#ConsumptionAccountInput').val();
        const bigCategoryId = $('#ConsumptionBigCategoryInput').val();
        const middleCategoryId = $('#ConsumptionMiddleCategoryInput').val();
        const memo = $('#ConsumptionMemoTextarea').val();

        // パラメータ構築
        const params = {
            money: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        }

        // リクエスト送信
        $.post(
            '/toppage-api/insert-consumption',
            params
        ).done(function (data) {
            console.log(data);
            getConsumption();
        }).fail(function () {
            console.log('Post失敗!!');
        }).always(function () {
            console.log('Post終了');
            $('#inputModal').modal('hide');
        });
    });

    // 収入情報保存ボタン押下時処理
    $('#IncomSaveButton').on('click', function() {

        // 入力値取得
        const date = $('#IncomDateInput').val();
        const money = $('#IncomMoneyInput').val();
        const accountId = $('#IncomAccountInput').val();
        const middleCategoryId = $('#IncomMiddleCategoryInput').val();
        const memo = $('#IncomMemotextarea').val();

        // パラメータ構築
        const params = {
            money: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        };

        // リクエスト送信
        $.post(
            '/toppage-api/insert-incom',
            params
        ).done(function(data) {
            console.log(data);
            getIncom();
            alert(data.message);
        }).fail(function() {
            console.log('Post失敗');
        }).always(function() {
            $('#inputModal').modal('hide');
        });
    });

    // 投資情報保存ボタン押下時処理
    $('#InvestmentSaveButton').on('click', function() {

        // 入力値取得
        const date = $('#InvestmentDateInput').val();
        const money = $('#InvestmentMoneyInput').val();
        const accountId = $('#InvestmentAccountInput').val();
        const middleCategoryId = $('#InvestmentMiddleCategoryInput').val();
        const memo = $('#InvestmentMemotextarea').val();

        // パラメータ構築
        const params = {
            money: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        };

        // リクエスト送信
        $.post(
            '/toppage-api/insert-investment',
            params
        ).done(function(data) {
            getInvestment();
            alert('投資情報登録成功!');
        }).fail(function() {
            console.log('Post失敗');
        }).always(function() {
            $('#inputModal').modal('hide');
        });
    });

    // 自己投資情報保存ボタン押下時処理
    $('#SelfInvestmentSaveButton').on('click', function() {

        // 入力値取得
        const date = $('#SelfInvestmentDateInput').val();
        const money = $('#SelfInvestmentMoneyInput').val();
        const accountId = $('#SelfInvestmentAccountInput').val();
        const middleCategoryId = $('#SelfInvestmentMiddleCategoryInput').val();
        const memo = $('#SelfInvestmentMemotextarea').val();

        // パラメータ構築
        const params = {
            money: money,
            accountId: accountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        };

        // リクエスト送信
        $.post(
            '/toppage-api/insert-self-investment',
            params
        ).done(function(data) {
            getSelfInvestment();
            alert('投資情報登録成功!');
        }).fail(function() {
            console.log('Post失敗');
        }).always(function() {
            $('#inputModal').modal('hide');
        });
    });
});

// 消費情報取得
function getConsumption() {
    $.get(
        '/toppage-api/get-consumption'
    ).done(function (data) {
        console.log('消費情報取得成功!');
        console.log(data);

        // 消費合計金額更新
        $('#TotalConsumption').text(data.totalConsumption);

        // トップページの消費情報書き換え
        $('#ConsumptionTable').find('tbody tr').remove();
        data.consumptionList.forEach(function (element) {
            console.log(element);
            html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>';
            $('#ConsumptionTable').append(html);
        });
    }).fail(function () {
        console.log('消費情報取得失敗!');
    }).always(function () {
        console.log('消費情報取得処理終了');
    });
};

// 収入情報取得
function getIncom() {
    $.get(
        '/toppage-api/get-incom'
    ).done(function (data) {
        console.log('収入情報取得成功!');
        console.log(data);

        // 収入合計金額更新
        $('#TotalIncom').text(data.totalIncom);

        // トップページの消費情報書き換え
        $('#IncomTable').find('tbody tr').remove();
        data.incomInfoList.forEach(function (element) {
            console.log(element);
            html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>';
            $('#IncomTable').append(html);
        });
    }).fail(function () {
        console.log('収入情報取得失敗!');
    }).always(function () {
        console.log('収入情報取得処理終了');
    });
};

// 投資情報取得
function getInvestment() {
    $.get(
        '/toppage-api/get-investment'
    ).done(function(data) {
        console.log('投資情報取得成功!');

        // 合計投資金額更新
        $('#TotalInvestment').text(data.totalInvestment);

        // トップページの投資情報書き換え
        $('#InvestmentTable').find('tbody tr').remove();
        data.investmentList.forEach(function (element) {
            console.log(element);
            html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>';
            $('#InvestmentTable').append(html);
        });
    }).fail(function () {
        console.log('投資情報取得失敗!');
    }).always(function () {
        console.log('投資情報取得処理終了');
    });
};

// 自己投資情報取得
function getSelfInvestment() {
    $.get(
        '/toppage-api/get-self-investment'
    ).done(function(data) {
        console.log('自己投資情報取得成功!');
        console.log(data);

        // 合計投資金額更新
        $('#TotalSelfInvestment').text(data.totalSelfInvestment);

        // トップページの投資情報書き換え
        $('#SelfInvestmentTable').find('tbody tr').remove();
        data.selfInvestmentList.forEach(function (element) {
            console.log(element);
            html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>';
            $('#SelfInvestmentTable').append(html);
        });
    }).fail(function () {
        console.log('投資情報取得失敗!');
    }).always(function () {
        console.log('投資情報取得処理終了');
    });
};

// ==========================================================================
// カテゴリ選択用のメモ書き
// ==========================================================================

// テスト用に変数の宣言
categories = [
    {
    bigcategoryName: '食費',
    middleCategoryList: [
            {
            categoryName: '朝ご飯',
            id: 14
            },
            {
            categoryName: '昼ごはん',
            id: 15
            }
        ]
    },
    {
    bigcategoryName: '日用品',
    middleCategoryList: [
            {
            categoryName: '子育て用品',
            id: 19
            },
            {
            categoryName: 'ドラッグストア',
            id: 20
            },
            {
            categoryName: 'お小遣い',
            id: 21
            }
        ]
    },
    {
    bigcategoryName: '趣味・娯楽',
    middleCategoryList: [
            {
            categoryName: 'アウトドア',
            id: 25
            },
            {
            categoryName: 'ゴルフ',
            id: 26
            },
            {
            categoryName: 'スポーツ',
            id: 27
            },
            {
            categoryName: '映画・音楽',
            id: 28
            }
        ]
    }
]

// 大項目が変更された時のアクション
// ここで中項目を変更する
$('#BigCategoryInput').change(function () {
    console.log('big category を変更');
});

// セレクトボックスの中身を全件削除
$('#MiddleCategoryInput > option').remove();

// categoriesの中の大項目をセレクトボックスに追加する
categories.forEach((elm) => {
    $('#BigCategoryInput').append($('<option>').html(elm.bigcategoryName));
});