export interface OrderModel {

    id?: string;

    customerId?: string;

    totalPrice?: number;

    status: 'UNPAID' | 'PAID' | 'SHIPPING'|'SHIPPED' | 'FINISHED' | 'REFUND' | 'CLOSED';

    createdTime?: string;

    updatedTime?: string;

    payMethod?: string;

    orderSource?: string;

    orderNum?: string;

    buyerComment?: string;

    sellerComment?: string;

    autoConfirmDays?: number;

    address?: ShippingAddressModel;
    
    items?: OrderItem[];

}

export interface OrderQueryModel{

}

export interface OrderItem {
    id?: string;

    customerId?: string;

    orderId?: string;

    skuId?: number;

    quantity?: number;

    productId?: string;

    unitPrice?: number;


    snapshotProductId?: string;

    subtotal?: number;

    createdTime?: Date;

    updatedTime?: Date;

}

export interface ShippingAddressModel {

    id?: string;

    orderId?: string;

    customerName?: string;

    postCode?: string;

    homeAddress?: string;

    phoneNumber?: string;

    createdTime?: Date;

    updatedTime?: Date;

}