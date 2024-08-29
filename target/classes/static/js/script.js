function fetchData(zone) {
    //console.log(`http://127.0.0.1:8080/SightAPI?zone=`+zone);
    //console.log("asdasdasd");
    const myDiv = document.getElementById('buttons');
    myDiv.innerHTML = `<button type="button" class="btn btn-primary" onclick="reset()">回選項</button>`;
    fetch(`/SightAPI?zone=`+zone)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            //console.log(response.json().photoUrl);
            return response.json();
        })
        .then(data => displayAttractions(data))
        .catch(error => console.error('Error fetching data:', error));
}

function displayAttractions(data) {
    //console.log(data);
    //console.log("asdansdkbqwkfbqlkwbflqbwfqwfq");
    const attractionsContainer = document.getElementById('attractions');
    attractionsContainer.innerHTML = ''; 
    var cnt=0;
    data.forEach(attraction => {
        //console.log(attraction.photoUrl);
        const card = document.createElement('div');
        card.className = 'col-md-4 mb-4';
        card.innerHTML = `
            <div class="card w-33">
                <div class="card-body">
                    <h5 class="card-title">${attraction.sightName}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${attraction.zone}</h6>
                    <p class="card-text">${attraction.category}</p>
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${cnt}">詳細資訊</button>
                    <a href="https://www.google.com/maps?q=${encodeURIComponent(attraction.address)}" class="btn btn-primary" target="_blank">地址</a>
                    <div id="collapse${cnt}" class="collapse mt-2">
                        <p>${attraction.description}</p>
                        <img src="${attraction.photoUrl}" class="img-fluid mb-2" alt="${attraction.sightName}">
                    </div>
                </div>
            </div>
        `;
        cnt+=1;
        attractionsContainer.appendChild(card);
    });
}

function reset(){
    //console.log("Asdasd");
    const myDiv = document.getElementById('buttons');
    myDiv.innerHTML = `<button type="button" class="btn btn-primary" onclick="fetchData('中山')">中山區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('信義')">信義區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('仁愛')">仁愛區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('中正')">中正區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('安樂')">安樂區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('七堵')">七堵區</button>
                <button type="button" class="btn btn-primary" onclick="fetchData('暖暖')">暖暖區</button>`;
    const cardDiv=document.getElementById('attractions');
    attractions.innerHTML="";
    return ;
}

function adjust(){
    const buttons=document.getElementById('buttons').querySelectorAll('button');
    const owo=document.getElementById('buttons');
    if(buttons.length>2){
        if(window.innerWidth<600){//button-list mb-4"
            owo.classList.add("button-list");
        }else {
            owo.classList.remove("button-list");
        }
    }
}



window.addEventListener('resize', adjust);
window.addEventListener('DOMContentLoaded', reset);