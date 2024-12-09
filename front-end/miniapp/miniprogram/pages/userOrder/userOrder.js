// pages/userOrder/userOrder.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    iconPath: ['https://i.postimg.cc/Kzzdssrt/orders.png', 'https://i.postimg.cc/W3HQR6XC/orders-selected.png', 'https://i.postimg.cc/wjzxbgtJ/user.png'],
    show: false,
    orderIndex: 0,
    order: {},
    orderTracking: {},
    deliveryAddress: {},
  },

  showOrderTracking() {
    this.setData({
      show: true,
    })
  },  
  
  onClose() {
    this.setData({
      show: false
    });
  },

  changeStatus() {
    if(this.data.order.currentStatus === '已下单') {
      wx.showLoading({
        title: '处理中...',
      })
      setTimeout(() => {
        this.setData({
          'order.currentStatus' : '已取消'
        })
        wx.request({
          url: 'http://localhost:8081/api/order/status',
          method: 'PATCH',
          data: {
            id: this.data.order.id,
            currentStatus: '已取消'
          },
          success:(res) => {
            this.setData({
              'orderTracking.cancelledTime' : res.data.data
            })
          },
          fail:() => {
            wx.showToast({
              title: '服务器繁忙',
              icon: 'error',
              duration: 1000
            })
          }
        })
        wx.hideLoading()
      }, 1000);
    } else {
      wx.showLoading({
        title: '处理中...',
      })
      setTimeout(() => {
        this.setData({
          'order.currentStatus' : '已确认'
        })
        wx.request({
          url: 'http://localhost:8081/api/order/status',
          method: 'PATCH',
          data: {
            id: this.data.order.id,
            currentStatus: '已确认'
          },
          success:(res) => {
            this.setData({
              'orderTracking.confirmedTime' : res.data.data
            })
          },
          fail:() => {
            wx.showToast({
              title: '服务器繁忙',
              icon: 'error',
              duration: 1000
            })
          }
        })
        wx.hideLoading()
      }, 1000);
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    for (let _i = 0; _i < app.globalData.orders.length; _i++) {
      if(app.globalData.orders[_i].id == options.id){
        this.setData({
          orderIndex: _i,
          order: app.globalData.orders[_i]
        })
        break
      }
    }
    // console.log(this.data.order)
    wx.request({
      url: app.globalData.urlPort + '/api/track',
      method: 'GET',
      data: {
        id: this.data.order.orderTrackingId
      },
      success:(res) => {
        this.setData({
          orderTracking: res.data.data
        })
      }
    })

    wx.request({
      url: app.globalData.urlPort + '/api/deliveryAddress/get',
      method: 'GET',
      data: {
        id: this.data.order.deliveryAddressId
      },
      success:(res) => {
        this.setData({
          deliveryAddress: res.data.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    wx.setNavigationBarTitle({
      title: '您的订单',
    })
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