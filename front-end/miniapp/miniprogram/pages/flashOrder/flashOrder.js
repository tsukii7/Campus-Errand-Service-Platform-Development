// pages/flashOrder/flashOrder.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {}, 
    description: "", //
    addressId: 0,
    pickupAddress: "", //
    userAddress: [],
    dAddress: ["请选择"],
    dAddressId: 0,
    deliveryAddress: "请选择", //
    expectedTime: "", 
    expectedTimeParse: "", //
    fee: null, //
    currentPage: 1,
    pageSize: 10,
  },
  
  onInputDescription: function(e) {
    if(e.detail.value.length > 10) {
      this.setData({
      description: null
      })
    } else {
    this.setData({
      description: e.detail.value
    })
  }
  },

  onInputPickupAddress: function(e) {
    this.setData({
      pickupAddress: e.detail.value
    })
  },
  
  onDeliveryAddressChange: function(e) {
    this.setData({
      dAddressId: e.detail.value,
      deliveryAddress: this.data.userAddress[e.detail.value - 1]
    })
  },

  onExpectedTimeChange: function(e) {
    this.setData({
      expectedTime: e.detail.dateString,
      expectedTimeParse: e.detail.date
    })
  },

  onInputFee: function(e) {
    if(isNaN(Number(e.detail.value))) {
      this.setData({
          fee: null
        })
    } else {
    this.setData({
      fee: e.detail.value
    })
  }
  },

  order: function() {
    if(this.data.description == "") {
        wx.showToast({
            title: '订单描述为空',
            icon: 'error',
            duration: 500
          })
      } else if (this.data.description == null) {
        wx.showToast({
            title: '订单描述过长',
            icon: 'error',
            duration: 500
          })
      } else if (this.data.pickupAddress == "") {
        wx.showToast({
            title: '取货地址为空',
            icon: 'error',
            duration: 500
          })
      } else if (this.data.deliveryAddress == "请选择") {
        wx.showToast({
            title: '请选择送货地址',
            icon: 'error',
            duration: 500
          })
      }
       else if (this.data.expectedTime == "") {
        wx.showToast({
            title: '请选择期望到达时间',
            icon: 'error',
            duration: 500
          })
      }  else if (this.data.fee == null) {
        wx.showToast({
            title: '请输入支付费用',
            icon: 'error',
            duration: 500
          })
      } else {
        wx.request({
            url: app.globalData.urlPort + '/api/order/add',
            method: 'POST',
            data: {
              clientId: app.globalData.user.id,
              pickUpAddress: this.data.pickupAddress,
              deliveryAddressId: this.data.deliveryAddress.id,
              serviceType: 2,
              expectedArrivedTime: this.data.expectedTimeParse,
              fee: this.data.fee,
              description: this.data.description,
            },
            success:(res) => {
                wx.showToast({
                    title: '下单成功',
                    icon: 'success',
                    duration: 1000
                  })
            }
          })
    console.log(this.data)
    wx.navigateBack()
    }
  },

  getDeliveryAddress() {
    wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/count',
        method: 'GET',
        data: {
          clientId: app.globalData.user.id
        },
        success:(res) => {
          this.setData({
            addressCnt: res.data.data,
          })
        }
      })

    wx.request({
        url: app.globalData.urlPort + '/api/deliveryAddress/page',
        method: 'GET',
        data: {
          clientId: app.globalData.user.id,
          current: this.data.currentPage,
          size: this.data.pageSize,
        },
        success:(res) => {
          this.setData({
            userAddress: res.data.data,
            dAddress: this.data.dAddress.concat(res.data.data.map(add => {
                return add.address + " " + add.receiver + " " + add.phoneNumber
              })),
          })
        }
      })

      while(this.data.currentPage * this.data.pageSize < this.data.addressCnt) {
        this.setData({
          'currentPage': this.data.currentPage + 1,
        })
          wx.request({
              url: app.globalData.urlPort + '/api/deliveryAddress/page',
              method: 'GET',
              data: {
                clientId: app.globalData.user.id,
                current: this.data.currentPage,
                size: this.data.pageSize,
              },
              success:(res) => {
                this.setData({
                    userAddress: this.data.userAddress.concat(res.data.data),
                    dAddress: this.data.dAddress.concat(res.data.data.map(add => {
                        return add.address + " " + add.receiver + " " + add.phoneNumber
                      })),
                })
              },
          })
      }
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
    this.getDeliveryAddress();
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