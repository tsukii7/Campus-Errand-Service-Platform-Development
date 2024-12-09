// pages/addAddress/addAddress.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {}, 
    receiver: "",
    phone: "",
    address: "",
  },

  onReceiverChange(e) {
    this.setData({
        receiver: e.detail.value
    })
  },

  onPhoneChange(e) {
    this.setData({
        phone: e.detail.value
    })
  },
  
  onAddressChange(e) {
    this.setData({
        address: e.detail.value
    })
  },

  addAddress() {
    wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/add',
        method: 'POST',
        data: {
          clientId: app.globalData.user.id,
          address: this.data.address,
          receiver: this.data.receiver,
          phoneNumber: this.data.phone
        },
        success:(res) => {
            wx.showToast({
            title: '添加成功',
            icon: 'success',
            duration: 1000
            })
        }
      })
    wx.navigateBack({

    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      user: app.globalData.user,
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