import 'bootstrap'
import $ from 'jquery'

$(document).ready(() => {
  $('#createUrlForm').on('submit', () => {
    let url = $('input[name=longUrl]').val()
    $.ajax({
      url: '/createUrl',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({longUrl: url}),
      success: (response) => {
        $('#longUrlText').html(url)
        $('#shortenedUrlText').html(response.aliasUrl)
        $('#shortenedUrlMessage').toggleClass('d-none')
        $('input[name=longUrl]').val('')
      }
    })
    return false
  })
})