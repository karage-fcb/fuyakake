const constant = {
    Consumption: 'Consumption',
    Incom: 'Incom',
    Investment: 'Investment',
    SelfInvestment: 'SelfInvestment',
    url: {
        api: '/toppage-api'
    }
}

let consumptionCategories;

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
        getConsumptionModalInfo();

        // モーダル表示
        $('#inputModal').modal('show');

    });

    // モーダルの消費タグ押下時処理
    $('#' + constant.Consumption + 'Link').on('click', function() {

        // 口座・カテゴリ情報の取得
        getConsumptionModalInfo();
    });

    // モーダルの収入タグ押下時処理
    $('#IncomLink').on('click', function() {

        $.get(
            constant.url.api + '/show-incom-modal'
        ).done(function(data) {
            console.log(data);
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.Incom, data.accounts);

            // カテゴリ情報書き換え
            rewriteMiddleCategory(constant.Incom, data.categories.middleCategories);
        });
    });

    // モーダルの投資タグ押下時処理
    $('#InvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            constant.url.api + '/show-investment-modal'
        ).done(function(data) {
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.Investment, data.accounts);

            // 投資先のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.Investment + 'To', data.accounts);

            // カテゴリ情報書き換え
            rewriteMiddleCategory(constant.Investment, data.categories.middleCategories)
        });
    });

    // モーダルの自己投資タグ押下時処理
    $('#SelfInvestmentLink').on('click', function() {

        // 口座・カテゴリ情報の取得
        $.get(
            constant.url.api + '/show-self-investment-modal'
        ).done(function(data) {
            // 口座のセレクトボックス書き換え
            rewriteAccountSelectBox(constant.SelfInvestment, data.accounts);

            // カテゴリ情報書き換え
            rewriteMiddleCategory(constant.SelfInvestment, data.categories.middleCategories);
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
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accounts);
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
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accounts);
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
        const toAccountId = $('#' + constant.Investment + 'ToAccountInput').val();
        const middleCategoryId = $('#' + constant.Investment + 'MiddleCategoryInput').val();
        const memo = $('#' + constant.Investment + 'Memotextarea').val();

        // パラメータ構築
        const params = {
            money: money,
            accountId: accountId,
            toAccountId: toAccountId,
            categoryId: middleCategoryId,
            memo: memo,
            date: date
        };

        // リクエスト送信
        $.post(
            constant.url.api + '/insert-investment',
            params
        ).done(function(data) {

            // 結果がエラーの時
            if (data.error) {
                alert(data.message);
            } else if(!data.error) {
                // 消費情報書き換え
                rewriteBalanceInfo(constant.Investment, data.toppageModel.totalInvestment, data.toppageModel.investmentList);

                // 口座情報書き換え
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accounts);
            }
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
            console.log('投資情報登録成功!');
            
            // 登録に失敗した時
            if (data.error) {
                alert(data.message);
            } else if (!data.error) {
                // 自己投資情報書き換え
                rewriteBalanceInfo(constant.SelfInvestment, data.toppageModel.totalSelfInvestment, data.toppageModel.selfInvestmentList);

                // 口座情報書き換え
                accountInfoRewriting(data.toppageModel.totalAsset, data.toppageModel.accounts);
            }
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
};

/**
 * トップページ口座情報の書き換え
 * 
 * @param {*} totalAsset  合計資産
 * @param {*} accounts 口座リスト
 */
function accountInfoRewriting(totalAsset, accounts) {
    // 資産合計金額更新
    $('#TotalAsset').text(totalAsset);

    console.log(accounts);
    console.log(totalAsset);

    // 口座情報書き換え
    $('#AssetTable').find('tbody tr').remove();
    accounts.forEach(function (element) {
        html = '<tr><th scope="row">' + element.accountName + '</th><td>' + element.assetAmount + '</td><td>' + element.assetAmount / totalAsset * 100 + '%</td></tr>';
        $('#AssetTable').append(html);
    });
};

/**
 * モーダルの口座のセレクトボックスを書き換え
 * @param {*} balance     収支種類
 * @param {*} accounts 口座リスト
 */
function rewriteAccountSelectBox(balance, accounts) {

    // セレクトボックスの中身を全件削除
    $('#' + balance + 'AccountInput > option').remove();
    // 口座情報をセレクトボックスに追加する
    accounts.forEach((elm) => {
        $('#' + balance + 'AccountInput').append($('<option>').html(elm.accountName).val(elm.accountId));
    });
};

/**
 * モーダルのカテゴリ情報を書き換える
 * @param {*} balance 
 * @param {*} categories 
 */
function rewriteMiddleCategory(balance, categories) {

    // セレクトボックスの中身を全削除
    $('#' + balance + 'MiddleCategoryInput > option').remove();
    // カテゴリ情報をセレクトボックスに追加する
    categories.forEach((elm) => {
        $('#' + balance + 'MiddleCategoryInput').append($('<option>').html(elm.categoryName).val(elm.categoryId));

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
};

/**
 * 消費情報入力モーダルが表示された時の処理
 */
function getConsumptionModalInfo() {
    
    // 口座・カテゴリ情報の取得
    $.get(
        constant.url.api + '/show-consumption-modal'
    ).done(function(data) {
        console.log(data);
        consumptionCategories = data.categories;
        
        // 消費入力モーダルの口座情報書き換え
        rewriteAccountSelectBox(constant.Consumption, data.accounts);

        // // 消費入力モーダルの大カテゴリの選択肢を削除
        $('#ConsumptionBigCategoryInput > option').remove();
        
        // 取得した大カテゴリをループして大カテゴリの選択肢を追加する
        consumptionCategories.forEach((elm, index) => {
            // もしカテゴリ名がundefinedだったら
            if (elm.categoryName !== undefined) {
                // 選択肢にカテゴリ情報を追加
                $('#ConsumptionBigCategoryInput').append($('<option>').html(elm.categoryName).val(elm.categoryId));
                // 最初のカテゴリの時は中カテゴリにも選択肢を追加
                if (index == 0) {
                    $('#ConsumptionMiddleCategoryInput > option').remove();
                    elm.middleCategories.forEach((midCategory) => {
                        $('#ConsumptionMiddleCategoryInput').append($('<option>').html(midCategory.categoryName).val(midCategory.categoryId));
                    });
                }
            }
        });        

        // 発火時処理を一旦削除
        $('#ConsumptionBigCategoryInput').off('change');

        // 消費モーダルの大カテゴリが変更された時に発火して中カテゴリを変更する
        $('#ConsumptionBigCategoryInput').on('change', function() {

            // 選択された大カテゴリIDを取得する
            bigCategoryId = parseInt($('#ConsumptionBigCategoryInput').val());
            
            // 選択された大カテゴリの情報を取得する
            bigCategory = $.grep(consumptionCategories,
                function(elm) {
                    return (elm.categoryId == bigCategoryId);
                }
            );

            // 選択された大カテゴリの情報から中カテゴリを書き換える
            $('#ConsumptionMiddleCategoryInput > option').remove();
            bigCategory[0].middleCategories.forEach((midCategory) => {
                $('#ConsumptionMiddleCategoryInput').append($('<option>').html(midCategory.categoryName).val(midCategory.categoryId));
            });
            
        });


    });
}

// ==========================================================================
// カテゴリ選択用のメモ書き
// ==========================================================================