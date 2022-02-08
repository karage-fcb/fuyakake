const constant = {
    Consumption: 'Consumption',
    Incom: 'Incom',
    Investment: 'Investment',
    SelfInvestment: 'SelfInvestment',
    url: {
        api: '/toppage-api'
    }
}

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
            constant.url.api + '/show-consumption-modal'
        ).done(function(data) {
            rewriteAccountSelectBox(constant.Consumption, data.accountList);
        });

        // モーダル表示
        $('#inputModal').modal('show');

    });

    // モーダルの消費タグ押下時処理
    $('#' + constant.Consumption + 'Link').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            constant.url.api + '/show-consumption-modal'
        ).done(function(data) {
            rewriteAccountSelectBox(constant.Consumption, data.accountList);
        });
    });

    // モーダルの収入タグ押下時処理
    $('#IncomLink').on('click', function() {

        $.get(
            constant.url.api + '/show-incom-modal'
        ).done(function(data) {
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.Incom, data.accountList);
        });
    });

    // モーダルの投資タグ押下時処理
    $('#InvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            constant.url.api + '/show-investment-modal'
        ).done(function(data) {
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.Investment, data.accountList);
        });
    });

    // モーダルの自己投資タグ押下時処理
    $('#SelfInvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            constant.url.api + '/show-self-investment-modal'
        ).done(function(data) {
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.SelfInvestment, data.accountList);
        });
    });

    // 消費情報保存ボタン押下時処理
    $('#' + constant.Consumption + 'SaveButton').on('click', function () {

        // 入力値チェック
        const msg = checkInput(constant.Consumption);

        // 入力値でアウトだったら
        if (msg != '') {
            alert(msg);
            return;
        }

        // 入力値取得
        const date = $('#' + constant.Consumption + 'DateInput').val();
        const money = $('#' + constant.Consumption + 'MoneyInput').val();
        const accountId = $('#' + constant.Consumption + 'AccountInput').val();
        const bigCategoryId = $('#' + constant.Consumption + 'BigCategoryInput').val();
        const middleCategoryId = $('#' + constant.Consumption + 'MiddleCategoryInput').val();
        const memo = $('#' + constant.Consumption + 'MemoTextarea').val();

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
            constant.url.api + '/insert-consumption',
            params
        ).done(function (data) {

            // 結果がエラーの時
            if (data.error) {
                alert(data.message);
            } else if(!data.error) {
                // 消費情報書き換え
                rewriteBalanceInfo(constant.Consumption, data.toppageModel.totalConsumption, data.toppageModel.consumptionList);

                // 口座情報書き換え
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accountList);
            }
        }).fail(function () {
            console.log('Post失敗!!');
        }).always(function () {
            console.log('Post終了');
            $('#inputModal').modal('hide');
        });
    });

    // 収入情報保存ボタン押下時処理
    $('#' + constant.Incom + 'SaveButton').on('click', function() {

        // 入力値チェック
        const msg = checkInput(constant.Incom);

        // 入力値チェックでアウトだったらエラーメッセージ出して終了
        if (msg != '') {
            alert(msg);
            return;
        }

        // 入力値取得
        const date = $('#' + constant.Incom + 'DateInput').val();
        const money = $('#' + constant.Incom + 'MoneyInput').val();
        const accountId = $('#' + constant.Incom + 'AccountInput').val();
        const middleCategoryId = $('#' + constant.Incom + 'MiddleCategoryInput').val();
        const memo = $('#' + constant.Incom + 'Memotextarea').val();

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
            constant.url.api + '/insert-incom',
            params
        ).done(function(data) {
            console.log(data);
            if (data.error){
                alert(data.message);
            } else if (!data.error) {
                // 収入情報書き換え
                rewriteBalanceInfo(constant.Incom, data.toppageModel.totalIncom, data.toppageModel.incomInfoList);

                // 口座情報書き換え
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accountList);
            }
            alert(data.message);
        }).fail(function() {
            console.log('Post失敗');
        }).always(function() {
            $('#inputModal').modal('hide');
        });
    });

    // 投資情報保存ボタン押下時処理
    $('#' + constant.Investment + 'SaveButton').on('click', function() {

        // 入力値チェック
        const msg = checkInput(constant.Investment);

        // 入力値でアウトだったら
        if (msg != '') {
            alert(msg);
            return;
        }

        // 入力値取得
        const date = $('#' + constant.Investment + 'DateInput').val();
        const money = $('#' + constant.Investment + 'MoneyInput').val();
        const accountId = $('#' + constant.Investment + 'AccountInput').val();
        const middleCategoryId = $('#' + constant.Investment + 'MiddleCategoryInput').val();
        const memo = $('#' + constant.Investment + 'Memotextarea').val();

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
            constant.url.api + '/insert-investment',
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
    $('#' + constant.SelfInvestment + 'SaveButton').on('click', function() {

        // 入力値チェック
        const msg = checkInput(constant.SelfInvestment);

        // 入力値でアウトだったら
        if (msg != '') {
            alert(msg);
            return;
        }

        // 入力値取得
        const date = $('#' + constant.SelfInvestment + 'DateInput').val();
        const money = $('#' + constant.SelfInvestment + 'MoneyInput').val();
        const accountId = $('#' + constant.SelfInvestment + 'AccountInput').val();
        const middleCategoryId = $('#' + constant.SelfInvestment + 'MiddleCategoryInput').val();
        const memo = $('#' + constant.SelfInvestment + 'Memotextarea').val();

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
            constant.url.api + '/insert-self-investment',
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

/**
 * 収支情報の書き換え
 * @param {*} balance 
 * @param {*} totalMoney 
 * @param {*} balanceList 
 */
function rewriteBalanceInfo(balance, totalMoney, balanceList) {

    // 合計金額更新
    $('#Total' + balance).text(totalMoney);

    // トップページの情報書き換え
    $('#' + balance + 'Table').find('tbody tr').remove();
    balanceList.forEach(function (element) {
        console.log(element);
        html = '<tr><th scope="row"></th><td>' + element.categoryName + '</td><td>' + element.price + '</td></tr>';
        $('#' + balance + 'Table').append(html);
    });
}

/**
 * トップページ口座情報の書き換え
 * 
 * @param {*} totalAsset  合計資産
 * @param {*} accountList 口座リスト
 */
function accountInfoRewriting(totalAsset, accountList) {
    // 資産合計金額更新
    $('#TotalAsset').text(totalAsset);

    console.log(accountList);
    console.log(totalAsset);

    // 口座情報書き換え
    $('#AssetTable').find('tbody tr').remove();
    accountList.forEach(function (element) {
        html = '<tr><th scope="row">' + element.accountName + '</th><td>' + element.assetAmount + '</td><td>' + element.assetAmount / totalAsset * 100 + '%</td></tr>';
        $('#AssetTable').append(html);
    });
};

/**
 * モーダルの口座のセレクトボックスを書き換え
 * @param {*} balance     収支種類
 * @param {*} accountList 口座リスト
 */
function rewriteAccountSelectBox(balance, accountList) {

    // セレクトボックスの中身を全件削除
    $('#' + balance + 'AccountInput > option').remove();
    // 口座情報をセレクトボックスに追加する
    accountList.forEach((elm) => {
        $('#' + balance + 'AccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
    });
};

/**
 * 日付と金額の入力値チェック
 * @param {} balance 
 * @returns 
 */
function checkInput(balance) {

    let msg = '';

    // 日付の入力値チェック
    const date = $('#' + balance + 'DateInput').val();
    if (date == '') {
        msg = '日付が未入力です';
        return msg;
    }

    // 金額の入力値チェック
    const money = $('#' + balance + 'MoneyInput').val();
    if(money == '') {
        msg = '金額が未入力です';
        return msg;
    }

    return msg;
}

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