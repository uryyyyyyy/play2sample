
export function toast(message: string) {
    if(typeof(Materialize) === 'undefined'){
        console.log("toast: " + message)
    }else{
        Materialize.toast(message, 4000);
    }
}