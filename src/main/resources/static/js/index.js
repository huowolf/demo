$('#table').bootstrapTable({
    url: '/user/getUserTable',
    pagination: true,
    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
    pageNumber:1,                       //初始化加载第一页，默认第一页
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
    search: true,
    columns: [{
      field: 'id',
      title: '用户Id'
    }, {
      field: 'name',
      title: '用户名'
    }, {
      field: 'phone',
      title: '电话'
    },{
        field: 'email',
        title: '邮箱'
    }]
  })