// pages/login/login.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: "",
    sum: 0,
  },

  tryAdmin:function () {
    this.setData({
      sum: this.data.sum + 1
    })
    if(this.data.sum === 10) {
      this.setData({
        sum: 0
      })
      wx.navigateTo({
        url: '/pages/adminLogin/adminLogin',
      })
    }
  },
  
  user_login:function () {
    wx.navigateTo({
      url: '/pages/userLogin/userLogin',
    })
  },

  rider_login:function () {
    wx.navigateTo({
      url: '/pages/riderLogin/riderLogin',
    })
  },

  sign_up:function () {
    wx.navigateTo({
      url: '/pages/sighUp/sighUp',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      user: app.globalData.user
    })
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