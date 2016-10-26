# xlistview
xlistview的基本使用

listView.setPullRefreshEnable(true);//可刷新
listView.setPullLoadEnable(true);//可加载
listView.setXListViewListener(iXListViewListener);//监听刷新和加载

  private XListView.IXListViewListener iXListViewListener = new XListView.IXListViewListener() {
        @Override // 下拉刷新的回调方法
        public void onRefresh() {
            isRefresh = true;
            // 下拉刷新,应该展示最新数据,所以清空集合
            list.clear();
            setData();

            // 设置刷新时间
            listView.setRefreshTime(dateFormat());
            arrayAdapter.notifyDataSetChanged();
            // 停止加载更多
            listView.stopLoadMore();
            // 停止刷新
            listView.stopRefresh();
        }

        // @Override // //上拉加载的回调方法
        public void onLoadMore() {
            isLoadData = true;
            setData();

            arrayAdapter.notifyDataSetChanged(); //停止加载更多
            listView.stopLoadMore(); //停止刷新
            listView.stopRefresh();
        }
    };
