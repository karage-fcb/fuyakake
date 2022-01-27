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
        $.get(
            '/toppage-api/get-category'
        ).done(function (data) {
            console.log(data);

            // 入力モーダル表示
            $('#inputModal').modal('show');
        }).fail(function () {
            alert('カテゴリ情報取得失敗');
        });
    });

    // 保存ボタン押下時処理
    $('#SaveButton').on('click', function () {
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
});

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
    })
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