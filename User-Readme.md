# 用户使用说明 - 校园跑腿服务平台 (Documentation for End User)

校园跑腿服务平台是面向南方科技大学校内的一款微信小程序，为用户/骑手两种需求的平台用户提供外卖代取、快递代取、闪送服务的下单/接单，并提供账号管理、个人信息管理等辅助功能。

The campus Errand-running service platform is a WeChat mini program for the Southern University of Science and Technology. It provides the ordering/receiving of takeout delivery, express delivery and flash delivery services for users/riders, and provides auxiliary functions such as account management and personal information management.

## 1. 账号管理(Account Management)

<img src="user_doc_img\1.jpg" alt="1" style="zoom:50%;" /> 

### 1.1 用户类别(User Categories)

#### 用户(Client)

 <img src="user_doc_img\2.jpg" alt="2" style="zoom:50%;" />

#### 骑手(Courier)

<img src="user_doc_img\3.jpg" alt="3" style="zoom:50%;" /> 

#### 管理员(Administrator)

<img src="user_doc_img\4.jpg" alt="4" style="zoom:50%;" /> 

### 1.2 使用说明(Instructions for Use)

#### 登录(Login)

已有账号的用户或骑手根据注册时的邮箱与密码登录。

Users or couriers with existing accounts log in according to their email and password when registering.

本平台对用户密码实现了SHA1算法加密，为用户和骑手提供账号安全保障。

This platform implements SHA1 algorithm encryption for user passwords to provide account security for users and couriers.

#### 注册(Register)

<img src="user_doc_img\5.jpg" alt="5" style="zoom:50%;" /> 

可使用邮箱进行账号注册，并选择自己的注册身份为用户/骑手。

You can use email to register your account and choose your registration identity as user/courier.

*管理员账号不对外开放注册。

\*Admin accounts are not open for registration.

## 2. 平台主要功能(Platform Main Functions)

### 2.1 用户使用说明(Instructions for Clients)

#### 2.1.1 下单功能(Place Orders)

<img src="user_doc_img\6.jpg" alt="6" style="zoom:50%;" /> 

用户可从主界面选择外卖代取、快递代取、闪送服务三种服务类型的下单。

Users can choose from the main interface to place orders with three service types: takeout delivery, express delivery and flash delivery service.

点击头像或昵称可跳转至个人主页界面。

Click on the avatar or nickname to jump to the personal home page interface.

##### 外卖代取(Takeout Service)

<img src="user_doc_img\7.jpg" alt="7" style="zoom:50%;" /> 

- 订单描述：填写简要的订单描述（不超过10字）

  Order Description: Fill in a brief order description (no more than 10 words)

- 取餐地址：从待选项中选择骑手的取餐地址（六号门等）

  Pick up address: Select the courier's pick up address (door 6, etc.) from the options to be selected.

- 送餐地址：从个人主页中填写的地址信息中选择送餐地址

  Delivery address: Select the delivery address from the address information filled in the personal homepage

- 外卖预计到达：选择外卖预计到达骑手取餐地点的时间

  Estimated delivery arrival: Select the time when the delivery is expected to arrive at the location where the courier picks up the food

- 期望到达时间：选择期望骑手送达外卖的时间

  Expected time of arrival: Choose the time at which the courier is expected to deliver the takeaway

- 商品价格：需代取外卖的价格

  Commodity price: the price of the delivery service

- 餐厅名称：需代取外卖的餐厅名称

  Restaurant name: The name of the restaurant to be picked up on behalf of the delivery

- 支付骑手费用：期望支付给代取骑手的费用

  Payment of rider fees: The expected payment to the pickup courier

##### 快递代取(Delivery Service)

<img src="user_doc_img\8.jpg" alt="8" style="zoom:50%;" /> 

- 取货地址：从待选项中选择骑手的取货地址（5栋收发室等）

  Pick-up address: Select the pick-up address of the courier from the to option (5 mailroom, etc.)

- 送货地址：从个人主页中填写的地址信息中选择送货地址

  Shipping address: Select the shipping address from the address information filled in the personal homepage

- 取件码：需代取快递的取件码

  Pick up code: The pick up code of the Courier is required

- 商品大致重量：输入商品的大致重量（单位：kg）

  Approximate weight of the product: Approximate weight of the input product (unit: kg)

- 商品大小：描述商品的大致大小

  Item size: Describes the approximate size of the item

*其余表项与外卖代取功能相同

*The rest of the table items have the same function as the takeout service

##### 闪送服务(Flash Service)

<img src="user_doc_img\9.jpg" alt="9" style="zoom:50%;" /> 

- 取货地址：填写骑手的取货地址

  Pick-up address: Fill in the pick-up address of the courier

*其余表项与外卖代取功能相同

*The rest of the table items have the same function as the takeout service

#### 2.2.2 订单管理功能(Order Management)

<img src="user_doc_img\10.jpg" alt="10" style="zoom:50%;" /> 

用户可查看全部订单，或按订单状态分类查看订单。

Users can view all orders or view orders by their status.

可点进卡片查看订单详情。

Click on the card to view the order details.

##### 待接单(Pending Orders)

<img src="user_doc_img\11.jpg" alt="11" style="zoom:50%;" /> 

无骑手接单，用户可选择取消订单，或到一定时间后自动取消订单。

There is no courier to take the order, the user can choose to cancel the order, or automatically cancel the order after a certain time.

骑手接单后不可取消订单。

The order cannot be cancelled after the courier has taken the order.

##### 待收货(Unreceived Orders)

<img src="user_doc_img\12.jpg" alt="12" style="zoom:50%;" /> 

在骑手接单后，用户确认送达前，订单均为待收货状态，用户可在收到订单后点击确认送达按钮确认收货，确认收货后，订单状态将变为已完成。

After the courier receives the order and before the user confirms the delivery, the order is in the state of pending receipt. The user can click the Confirm delivery button to confirm the receipt of the order after receiving the order. After confirming the receipt, the order status will change to completed.

##### 订单追踪(Order Tracking)

<img src="user_doc_img\13.jpg" alt="13" style="zoom:50%;" /> 

点击订单详情页上方的订单状态，可查看当前订单的订单追踪信息。

Click the order status at the top of the order details page to view the order tracking information for the current order.

### 2.2 骑手使用说明(Instructions for Couriers)

#### 2.2.1 接单功能(Take Orders)

<img src="user_doc_img\18.jpg" alt="18" style="zoom:50%;" /> 

骑手可在主界面点击接单按钮，进行三种服务现有订单的接单，可在左侧切换服务类别。

Couriers can click the order order button on the main interface to order existing orders of the three services, and the service category can be switched on the left.

骑手也可点击卡片进入订单详情页，查看详情后点击接取订单。

Couriers can also click the card to enter the order details page, check the details and click to receive the order.

<img src="user_doc_img\19.jpg" alt="19" style="zoom:50%;" /> 

#### 2.2.2 订单管理功能(Order Management)

与用户相同，骑手可查看目前接取的所有订单，或根据订单状态分类查看订单。

Like the user, the courier can view all the orders taken so far, or view the orders according to their order status.

骑手同样可以在订单订单详情页面确认订单送达，并等待用户确认。

The courier can also confirm the order delivery on the order details page and wait for the user's confirmation.

骑手接取订单后不可取消。

The order cannot be cancelled after the courier picks it up.

## 3. 其他功能(Other Functions)

<img src="user_doc_img\14.jpg" alt="14" style="zoom:50%;" /> 

用户/骑手的个人主页，可以进行个人信息与地址信息的管理。

The user/courier's personal homepage can manage personal information and address information.

### 3.1 个人信息管理(Personal Information Management)

点击头像/昵称/个人信息，均可进入个人信息管理页面。

Click avatar/nickname/personal information, you can enter the personal information management page.

<img src="user_doc_img\15.jpg" alt="15" style="zoom:50%;" /> 

可在该界面进行头像及用户名的修改。

You can modify your avatar and user name in this interface.

### 3.2 地址信息管理(Address Information Management)

点击地址管理，可进入收货地址管理界面。

Click address management to enter the receiving address management interface.

<img src="user_doc_img\16.jpg" alt="16" style="zoom:50%;" /> 

在该界面可查看目前创建的所有收货地址，并可进行删除操作。

In this interface, you can view all the shipping addresses created so far, and you can delete them.

点击添加地址，可创建新的收货地址。

Click Add address to create a new delivery address.

<img src="user_doc_img\17.jpg" alt="17" style="zoom:50%;" /> 

可在该界面添加收货人、联系电话及详细收货地址信息。

You can add consignee, contact phone number and detailed delivery address information in this interface.