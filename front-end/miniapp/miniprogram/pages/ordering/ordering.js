// pages/ordering/ordering.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperList: [
      {id: 1, image: 'https://i.postimg.cc/85t72GK8/nkd1.jpg'},
      {id: 2, image: 'https://i.postimg.cc/5yyX1rTX/nkd2.jpg'},
      {id: 3, image: 'https://i.postimg.cc/mkntrwW8/nkd3.jpg'}
    ],
    user: {
    }
  },

  takeoutOrder() {
    wx.navigateTo({
      url: '/pages/takeoutOrder/takeoutOrder',
    })
  },

  deliveryOrder() {
    wx.navigateTo({
      url: '/pages/deliveryOrder/deliveryOrder',
    })
  },

  flashOrder() {
    wx.navigateTo({
      url: '/pages/flashOrder/flashOrder',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.setData({
      user: getApp().globalData.user
    })
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: "ordering"
      })
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})