function setupHistoryDataTable(el) {
    const table = $(el).DataTable({
        dom: 'Bfrtip',
    });

    table.columns().flatten().each(function (colIdx) {
        // Create the select list and search operation
        var select = $('<select />')
                .addClass('form-control')
                .appendTo(
                        table.column(colIdx).footer()
                        )
                .on('change', function () {
                    table
                            .column(colIdx)
                            .search($(this).val())
                            .draw();
                });
        appendFilterOptions(table, colIdx, select);
        // Get the search data for the first column and add to the select list
    });
    table.on('draw.dt', function () {
        table.columns().flatten().each(function (colIdx) {
            const select = $(table.column(colIdx).footer()).find('select');
            const selectedVal = select.val();
            select.empty();
            appendFilterOptions(table, colIdx, select);
            select.val(selectedVal);
        });
    });

    function appendFilterOptions(table, colIdx, select) {
        table
                .column(colIdx, {search: 'applied'})
                .data()
                .sort()
                .unique()
                .each(function (d) {
                    select.append($('<option value="' + d + '">' + d + '</option>'));
                });
        select.prepend("<option value='' selected>-</option>");
    }
}

function adjustDataTableWidth() {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $($.fn.dataTable.tables(true)).DataTable().columns.adjust();
    });
}