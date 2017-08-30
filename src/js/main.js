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
        $('#longUrlLink').html(url).attr('href', url)
        $('#shortenedUrlLink').html(response.aliasUrl).attr('href', url)
        $('#shortenedUrlMessage').removeClass('d-none')
        $('input[name=longUrl]').val('')
      }
    })
    return false
  })
})