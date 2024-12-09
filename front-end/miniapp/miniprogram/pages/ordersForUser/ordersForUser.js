// pages/ordersForUser/ordersForUser.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['全部','待接单','待收货','已完成','已取消'],
    currentTab: 0,
    navScrollLeft: 0,
    iconPath: ['https://i.postimg.cc/J7gGxfcc/takeAway.png', 'https://i.postimg.cc/KYnPhZQ2/express.png', 'https://i.postimg.cc/yx6XTwmz/delivery.png'],
    ordersStatusAll: [],
    ordersStatus0: [],
    ordersStatus1: [],
    ordersStatus2: [],
    ordersStatus3: [],
    currentPage: [1, 1, 1, 1, 1],
    pageSize: [10, 10, 10, 10, 10],
    totalOrders: [0, 0, 0, 0, 0],
    isLoading: [false, false, false, false, false],
  },

  getOrders() {
    // this.setData({
    //   ordersStatusAll: app.globalData.orders,
    //   ordersStatus0: [],
    //   ordersStatus1: [],
    //   ordersStatus2: [],
    //   ordersStatus3: []
    // })
    // for (let _i = 0; _i < this.data.ordersStatusAll.length; _i++) {
    //   const _e = this.data.ordersStatusAll[_i];
    //   if(_e.status.statusNumber === 0) this.setData({ ordersStatus0: [...this.data.ordersStatus0, _e] })
    //   else if(_e.status.statusNumber === 1) this.setData({ ordersStatus1: [...this.data.ordersStatus1, _e] })
    //   else if(_e.status.statusNumber === 2) this.setData({ ordersStatus2: [...this.data.ordersStatus2, _e] })
    //   else this.setData({ ordersStatus3: [...this.data.ordersStatus3, _e] })
    // }

    this.setData({
      currentPage: [1, 1, 1, 1, 1],
      isLoading: [false, false, false, false, false],
    })

    // 获取订单数量
    // 全部
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/client/all',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[0]': res.data.data
        })
      }
    })

    // 待接单
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/client/unaccepted',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[1]': res.data.data
        })
      }
    })

    // 待收货
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/client/unconfirmed',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[2]': res.data.data
        })
      }
    })

    // 已完成
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/client/confirmed',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[3]': res.data.data
        })
      }
    })

    // 已取消
    wx.request({
      url: app.globalData.urlPort + '/api/order/count/client/cancelled',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id
      },
      success:(res) => {
        this.setData({
          'totalOrders[4]': res.data.data
        })
      }
    })

    // 获取订单
    // 全部
    wx.request({
      url: app.globalData.urlPort + '/api/order/client/all',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id,
        current: this.data.currentPage[0],
        size: this.data.pageSize[0],
      },
      success:(res) => {
        this.setData({
          ordersStatusAll: res.data.data
        })
        for (let i = 0; i < this.data.ordersStatusAll.length; i++) {
          const e = this.data.ordersStatusAll[i];
          if(e.courierId == null) continue;
          wx.request({
            url: app.globalData.urlPort + '/api/courier/get',
            method: 'GET',
            data: {
              id: e.courierId
            },
            success:(res) => {
              this.setData({
                ['ordersStatusAll[' + i + '].courier'] : res.data.data
              })
              app.globalData.orders = this.data.ordersStatusAll
            }
          })
        }
      }
    })
    
    // 待接单
    wx.request({
      url: app.globalData.urlPort + '/api/order/client/unaccepted',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id,
        current: this.data.currentPage[1],
        size: this.data.pageSize[1],
      },
      success:(res) => {
        this.setData({
          ordersStatus0: res.data.data
        })
        for (let i = 0; i < this.data.ordersStatus0.length; i++) {
          const e = this.data.ordersStatus0[i];
          if(e.courierId == null) continue;
          wx.request({
            url: app.globalData.urlPort + '/api/courier/get',
            method: 'GET',
            data: {
              id: e.courierId
            },
            success:(res) => {
              this.setData({
                ['ordersStatus0[' + i + '].courier'] : res.data.data
              })
            }
          })
        }
      }
    })

    // 待收货
    wx.request({
      url: app.globalData.urlPort + '/api/order/client/unconfirmed',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id,
        current: this.data.currentPage[2],
        size: this.data.pageSize[2],
      },
      success:(res) => {
        this.setData({
          ordersStatus1: res.data.data
        })
        for (let i = 0; i < this.data.ordersStatus1.length; i++) {
          const e = this.data.ordersStatus1[i];
          if(e.courierId == null) continue;
          wx.request({
            url: app.globalData.urlPort + '/api/courier/get',
            method: 'GET',
            data: {
              id: e.courierId
            },
            success:(res) => {
              this.setData({
                ['ordersStatus1[' + i + '].courier'] : res.data.data
              })
            }
          })
        }
      }
    })

    // 已完成
    wx.request({
      url: app.globalData.urlPort + '/api/order/client/confirmed',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id,
        current: this.data.currentPage[3],
        size: this.data.pageSize[3],
      },
      success:(res) => {
        this.setData({
          ordersStatus2: res.data.data
        })
        for (let i = 0; i < this.data.ordersStatus2.length; i++) {
          const e = this.data.ordersStatus2[i];
          if(e.courierId == null) continue;
          wx.request({
            url: app.globalData.urlPort + '/api/courier/get',
            method: 'GET',
            data: {
              id: e.courierId
            },
            success:(res) => {
              this.setData({
                ['ordersStatus2[' + i + '].courier'] : res.data.data
              })
            }
          })
        }
      }
    })

    // 已取消
    wx.request({
      url: app.globalData.urlPort + '/api/order/client/cancelled',
      method: 'GET',
      data: {
        clientId: app.globalData.user.id,
        current: this.data.currentPage[4],
        size: this.data.pageSize[4],
      },
      success:(res) => {
        this.setData({
          ordersStatus3: res.data.data
        })
        for (let i = 0; i < this.data.ordersStatus3.length; i++) {
          const e = this.data.ordersStatus3[i];
          if(e.courierId == null) continue;
          wx.request({
            url: app.globalData.urlPort + '/api/courier/get',
            method: 'GET',
            data: {
              id: e.courierId
            },
            success:(res) => {
              this.setData({
                ['ordersStatus3[' + i + '].courier'] : res.data.data
              })
            }
          })
        }
      }
    })
  },

  onScrollToLowerAll() {
    if(this.data.isLoading[0]) return
    if(this.data.currentPage[0] * this.data.pageSize[0] < this.data.totalOrders[0]) {
      this.setData({
        'currentPage[0]': this.data.currentPage[0] + 1,
        'isLoading[0]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/client/all',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage[0],
              size: this.data.pageSize[0],
            },
            success:(res) => {
              this.setData({
                ordersStatusAll: this.data.ordersStatusAll.concat(res.data.data)
              })
              for (let i = (this.data.currentPage[0]-1)*this.data.pageSize[0]; i < this.data.ordersStatusAll.length; i++) {
                const e = this.data.ordersStatusAll[i];
                if(e.courierId == null) continue;
                wx.request({
                  url: app.globalData.urlPort + '/api/courier/get',
                  method: 'GET',
                  data: {
                    id: this.data.ordersStatusAll[i].courierId
                  },
                  success:(res) => {
                    this.setData({
                      ['ordersStatusAll[' + i + '].courier'] : res.data.data
                    })
                  }
                })
              }
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading[0]': false
              })
            }
        })
      }, 500);
      app.globalData.orders = this.data.ordersStatusAll
    }
  },

  onScrollToLower0() {
    if(this.data.isLoading[1]) return
    if(this.data.currentPage[1] * this.data.pageSize[1] < this.data.totalOrders[1]) {
      this.setData({
        'currentPage[1]': this.data.currentPage[1] + 1,
        'isLoading[1]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/client/unaccepted',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage[1],
              size: this.data.pageSize[1],
            },
            success:(res) => {
              this.setData({
                ordersStatus0: this.data.ordersStatus0.concat(res.data.data)
              })
              for (let i = (this.data.currentPage[1]-1)*this.data.pageSize[1]; i < this.data.ordersStatus0.length; i++) {
                const e = this.data.ordersStatus0[i];
                if(e.courierId === null) continue
                wx.request({
                  url: app.globalData.urlPort + '/api/courier/get',
                  method: 'GET',
                  data: {
                    id: e.courierId
                  },
                  success:(res) => {
                    this.setData({
                      ['ordersStatus0[' + i + '].courier'] : res.data.data
                    })
                  }
                })
              }
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading[1]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower1() {
    if(this.data.isLoading[2]) return
    if(this.data.currentPage[2] * this.data.pageSize[2] < this.data.totalOrders[2]) {
      this.setData({
        'currentPage[2]': this.data.currentPage[2] + 1,
        'isLoading[2]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/client/unconfirmed',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage[2],
              size: this.data.pageSize[2],
            },
            success:(res) => {
              this.setData({
                ordersStatus1: this.data.ordersStatus1.concat(res.data.data)
              })
              for (let i = (this.data.currentPage[2]-1)*this.data.pageSize[2]; i < this.data.ordersStatus1.length; i++) {
                const e = this.data.ordersStatus1[i];
                if(e.courierId === null) continue
                wx.request({
                  url: app.globalData.urlPort + '/api/courier/get',
                  method: 'GET',
                  data: {
                    id: e.courierId
                  },
                  success:(res) => {
                    this.setData({
                      ['ordersStatus1[' + i + '].courier'] : res.data.data
                    })
                  }
                })
              }
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading[2]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower2() {
    if(this.data.isLoading[3]) return
    if(this.data.currentPage[3] * this.data.pageSize[3] < this.data.totalOrders[3]) {
      this.setData({
        'currentPage[3]': this.data.currentPage[3] + 1,
        'isLoading[3]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/client/confirmed',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage[3],
              size: this.data.pageSize[3],
            },
            success:(res) => {
              this.setData({
                ordersStatus2: this.data.ordersStatus2.concat(res.data.data)
              })
              for (let i = (this.data.currentPage[3]-1)*this.data.pageSize[3]; i < this.data.ordersStatus2.length; i++) {
                const e = this.data.ordersStatus2[i];
                if(e.courierId === null) continue
                wx.request({
                  url: app.globalData.urlPort + '/api/courier/get',
                  method: 'GET',
                  data: {
                    id: e.courierId
                  },
                  success:(res) => {
                    this.setData({
                      ['ordersStatus2[' + i + '].courier'] : res.data.data
                    })
                  }
                })
              }
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading[3]': false
              })
            }
        })
      }, 500);
    }
  },

  onScrollToLower3() {
    if(this.data.isLoading[4]) return
    if(this.data.currentPage[4] * this.data.pageSize[4] < this.data.totalOrders[4]) {
      this.setData({
        'currentPage[4]': this.data.currentPage[4] + 1,
        'isLoading[4]': true,
      })
      wx.showLoading({
        title: "数据加载中..."
      });
      setTimeout(() => {
        wx.request({
            url: app.globalData.urlPort + '/api/order/client/cancelled',
            method: 'GET',
            data: {
              clientId: app.globalData.user.id,
              current: this.data.currentPage[4],
              size: this.data.pageSize[4],
            },
            success:(res) => {
              this.setData({
                ordersStatus3: this.data.ordersStatus3.concat(res.data.data)
              })
              for (let i = (this.data.currentPage[4]-1)*this.data.pageSize[4]; i < this.data.ordersStatus3.length; i++) {
                const e = this.data.ordersStatus3[i];
                if(e.courierId === null) continue
                wx.request({
                  url: app.globalData.urlPort + '/api/courier/get',
                  method: 'GET',
                  data: {
                    id: e.courierId
                  },
                  success:(res) => {
                    this.setData({
                      ['ordersStatus3[' + i + '].courier'] : res.data.data
                    })
                  }
                })
              }
            },
            fail:() => {
              wx.showToast({
                title: '服务器繁忙',
                icon: 'error',
                duration: 1000
              })
            },
            complete:() => {
              wx.hideLoading()
              this.setData({
                'isLoading[4]': false
              })
            }
        })
      }, 500);
    }
  },

  navbarTap: function(e){
    var cur = e.currentTarget.dataset.current; 
    var singleNavWidth = this.data.windowWidth / 5;
    this.setData({
        navScrollLeft: (cur - 2) * singleNavWidth
    })      
    if (this.data.currentTab == cur) {
        return false;
    } else {
        this.setData({
            currentTab: cur
        })
    }
  },

  switchNav(e) {
    var cur = e.detail.current;
    var singleNavWidth = this.data.windowWidth / 5;
    this.setData({
      currentTab: cur,
      navScrollLeft: (cur - 2) * singleNavWidth
    });
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
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: "ordersForUser"
      })
    }
    this.getOrders()
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
    wx.stopPullDownRefresh()
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