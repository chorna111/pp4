const getProducts = () => {
  return fetch("/api/products")
  .then(response => response.json());
}

const getCurrentOffer = () => {
  return fetch("/api/current-offer")
    .then(response => response.json());
}

const addProductToCart=(productId)=>{
    return fetch(`/api/add-to-cart/${productId}`,{
        method: 'POST'
    });
}

const acceptOffer=(acceptOfferRequest)=>{
    return fetch("/api/accept-offer",{
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(acceptOfferRequest)
    }).then(response=>response.json());
}








const createHtmlEl = (productData) => {
    const template = `
        <div class="product">
            <h4>${productData.name}</h4>
            <img src="https://picsum.photos/id/237/200/300"/>
            <div class="product__price">
                <span>${productData.price}</span>
                <button data-id="${productData.id}">Add to cart</button>
            </div>
        </div>

    `

    const productEl = document.createElement('li');
    productEl.innerHTML = template.trim();

    return productEl;
}
const refreshCurrentOffer=()=>{
    const totalEl=document.querySelector('#offer__total');
    const itemsCountEl=document.querySelector('#offer__items-count');
    getCurrentOffer()
        .then(offer=>{
            totalEl.textContent=`${offer.total} PLN`;
            itemsCountEl.textContent=`${offer.itemsCount}`;
        })
    
}






const initializeCartHandler=(productHtmlEl)=>{
    const addToCartBtn=productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click",(event)=>{
    const productId=event.target.getAttribute("data-id");
    addProductToCart(productId)
        .then(refreshCurrentOffer());
    });
    return productHtmlEl;
}
const checkoutFormEl=document.querySelector('#checkout');
checkoutFormEl.addEventListener("submit",(event)=>{
    event.preventDefault();
    const acceptOfferRequest={
        firstName: checkoutFormEl.querySelector('input[name="first_name"]').value,
        lastName: checkoutFormEl.querySelector('input[name="lastname_name"]').value,
        email: checkoutFormEl.querySelector('input[name="email"]').value,
    }
    acceptOffer(acceptOfferRequest)
        .then(resDet=> window.location.href=resDet.paymentUrl);
});


document.addEventListener("DOMContentLoaded", () => {
    const productsList= document.querySelector("#productsList");
    getProducts()
        .then(productsAsJson => productsAsJson.map(createHtmlEl))
        .then(productsAsHtmlEl=>productsAsHtmlEl.map(initializeCartHandler))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(htmlEl => productsList.appendChild(htmlEl));
        });
});

