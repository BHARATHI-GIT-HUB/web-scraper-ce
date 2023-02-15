document.getElementById("getUrl").onclick = getCurrUrl;
document.getElementById("pushUrl").onclick = PushData;
document.getElementById("fetchData").onclick = fetchData;

function getCurrUrl() {
  chrome.tabs.query(
    {
      active: true,
      lastFocusedWindow: true,
    },
    function (tabs) {
      const tab = tabs[0];

      const xhr = new XMLHttpRequest();

      xhr.open("GET", tab.url, true);
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
          const parser = new DOMParser();
          const htmlDoc = parser.parseFromString(xhr.responseText, "text/html");
          const title = htmlDoc.querySelector("title").textContent;
          document.getElementById("title").innerHTML = title;
        } else {
          document.getElementById("title").innerHTML = "This Web Is Resticted";
        }
      };
      xhr.send();

      document.getElementById("url").innerHTML = tab.url;
      console.log(tab.url);
    }
  );
}

async function PushData() {
  const url = document.getElementById("url").innerHTML;
  alert(url);
  await fetch("http://localhost:8080/", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(url),
  }).then((res) => {
    // document.getElementById("responseMessage").innerHTML = res;
    console.log("Request complete! response:", res);
  });
}

async function fetchData() {
  const res = await fetch("http://localhost:8080/");

  const record = await res.json();
  console.log(record);

  document.getElementById("fetchMessage").innerHTML = record.map((item) => {
    return `<li>${item.name}</li>`;
  });
}
